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

import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class BorrowServiceImpl implements BorrowService {
	@Autowired
	StudentMapper studentMapper;
	@Autowired
	BorrowMapper borrowMapper;
	@Autowired
	BookMapper bookMapper;
	@Override
	public Student queryStudentAndStudentBorrowStatusBySid(Integer sid) {
		Student student = studentMapper.queryStudentBySid(sid);

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
			int i1 = bookMapper.updateBookInventoryAndBorrowNumById(borrow.getBid());
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
