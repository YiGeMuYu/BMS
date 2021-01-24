package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.BookMapper;
import com.muyu.bms.mapper.BorrowMapper;
import com.muyu.bms.mapper.StudentMapper;
import com.muyu.bms.service.BorrowService;
import com.muyu.bms.vo.Borrow;
import com.muyu.bms.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Service
public class BorrowServiceImpl implements BorrowService {
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	BorrowMapper borrowMapper;
	@Autowired
	BookMapper bookMapper;

	/*
		工具方法 start
	 */

	/**
	 * 输入两个时间，一个是开始时间，另一个是结束时间
	 * 两者相比较，判断是不是超过31天
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean checkOverdue(String startTime ,String endTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date end = sdf.parse(endTime);
			Date start = sdf.parse(startTime);
			long day = 60*1000*60*24; // 1天
			if(end.getTime()-(day*31) > start.getTime()){
				return true;
			}else{
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 根据sid查询出这个学生的借书记录
	 * 然后根据借书记录中的借书时间和归还时间判断逾期
	 * 逾期分为下面三种情况
	 * 1. 没借过书，不会逾期
	 * 2. 借了书还没还
	 *    取出借书时间，然后取出当前时间，如果不超过31天，则没逾期
	 *    取出借书时间，然后取出当前时间，如果超过31天，则逾期
	 * 3. 借了书并且还了书
	 *    直接计算借取时间，如果超过31天，则逾期
	 * @param sid
	 * @return
	 */
	public String checkOverdueBySid(int sid){
		int num=0;
		List<Borrow> borrows = borrowMapper.queryBorrowBySid(sid);
		for (Borrow borrow : borrows) {
			System.out.println(borrow);
		}

		if(borrows==null) {
			return "无逾期记录";
		}else{
			for (Borrow borrow : borrows) {
				if(borrow!=null) {
					if ("".equals(borrow.getReturnTime()) || borrow.getReturnTime() == null) {
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String nowTime = sdf.format(date);
						if (checkOverdue(borrow.getBorrowTime(), nowTime)) {
							num += 1;
						}
					} else {
						if (checkOverdue(borrow.getBorrowTime(), borrow.getReturnTime())) {
							num += 1;
						}
					}
				}
			}
			if(num==0){
				return "无逾期记录";
			}else{
				return "逾期"+num+"次";
			}
		}

	}
	/*
		工具方法 end
	 */
	@Override
	public Student queryStudentAndStudentBorrowStatusBySid(Integer sid) {
		Student student = studentMapper.queryStudentBySid(sid);
		student.setStudentBorrowStatus(checkOverdueBySid(sid));
		return student;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean borrowBook(Borrow borrow) {
		Date date = new Date();
		//设置好SimpleDateFormat的格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前时间，此时时间的格式为String
		String source = df.format(date);
		borrow.setBorrowTime(source);
		int i = borrowMapper.borrowBook(borrow);
		if(i>0){
			int i1 = bookMapper.updateBorrowBookInventoryAndBorrowNumById(borrow.getBid());
			if(i1>0){
				return true;
			}else{
				try {
					throw new Exception("更改book中的inventory和borrownum失败，数据会滚");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public List<Borrow> queryBorrowBookListBySid(Integer sid) {
		List<Borrow> borrows = borrowMapper.queryBorrowBookListBySid(sid);
		for (Borrow borrow : borrows) {
			String borrowTime = borrow.getBorrowTime();
			borrow.setBorrowTime(borrowTime.substring(0, borrowTime.indexOf(" ")));
		}

		return borrows;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public boolean returnBook(int borrowId, String bid) {
		Borrow borrow = new Borrow();
		borrow.setBorrowId(borrowId);
		Date date = new Date();
		//设置好SimpleDateFormat的格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//获取当前时间，此时时间的格式为String
		String source = df.format(date);
		borrow.setReturnTime(source);
		int i = borrowMapper.returnBook(borrow);
		if(i>0){
			int i1 = bookMapper.updateReturnBookInventoryAndBorrowNumById(bid);
			if(i1>0){
				return true;
			}else{
				try {
					throw new Exception("更改book中的inventory和borrownum失败，数据会滚");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
