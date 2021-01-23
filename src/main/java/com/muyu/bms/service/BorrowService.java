package com.muyu.bms.service;

import com.muyu.bms.vo.Borrow;
import com.muyu.bms.vo.Student;

public interface BorrowService {
	//查询学生信息，以及该学生有无逾期记录
	Student queryStudentAndStudentBorrowStatusBySid(Integer sid);

	//借阅书籍
	boolean borrowBook(Borrow borrow);
}
