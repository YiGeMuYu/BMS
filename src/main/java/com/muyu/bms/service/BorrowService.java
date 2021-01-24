package com.muyu.bms.service;

import com.muyu.bms.vo.Borrow;
import com.muyu.bms.vo.Student;

import java.util.List;

public interface BorrowService {
	//查询学生信息，以及该学生有无逾期记录
	Student queryStudentAndStudentBorrowStatusBySid(Integer sid);

	//借阅书籍
	boolean borrowBook(Borrow borrow);

	List<Borrow> queryBorrowBookListBySid(Integer sid);

	boolean returnBook(int borrowId,String bid);

	List<Borrow> queryBorrowBookListBySidAndBorrowStatus(Integer sid);
}
