package com.muyu.bms.service;

import com.muyu.bms.vo.Student;

import java.util.List;

public interface StudentService {
	Student checkLogin(int sid);

	List<Student> queryAllStudent();

	Student queryStudentBySid(int sid);
}
