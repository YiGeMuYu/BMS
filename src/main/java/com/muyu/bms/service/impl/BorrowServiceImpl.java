package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.BookMapper;
import com.muyu.bms.mapper.BorrowMapper;
import com.muyu.bms.mapper.StudentMapper;
import com.muyu.bms.service.BorrowService;
import com.muyu.bms.util.BmsUtil;
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

	BmsUtil bmsUtil = new BmsUtil();
	@Override
	public Student queryStudentAndStudentBorrowStatusBySid(Integer sid) {
		Student student = studentMapper.queryStudentBySid(sid);
		student.setStudentBorrowStatus(bmsUtil.checkOverdueBySid(sid, borrowMapper.queryBorrowBySid(sid)));
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
					throw new Exception("更改book中的inventory和borrownum失败，数据回滚");
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
			System.out.println(borrow.getReturnTime());
			if(borrow.getReturnTime()!=null){
				String returnTime = borrow.getReturnTime();
				borrow.setReturnTime(returnTime.substring(0, returnTime.indexOf(" ")));
			}
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
					throw new Exception("更改book中的inventory和borrownum失败，数据回滚");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public List<Borrow> queryBorrowBookListBySidAndBorrowStatus(Integer sid) {
		List<Borrow> borrows = borrowMapper.queryBorrowBookListBySidAndBorrowStatus(sid);
		for (Borrow borrow : borrows) {
			String borrowTime = borrow.getBorrowTime();
			borrow.setBorrowTime(borrowTime.substring(0, borrowTime.indexOf(" ")));
		}

		return borrows;
	}
}
