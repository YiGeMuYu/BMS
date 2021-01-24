package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.BorrowMapper;
import com.muyu.bms.mapper.StudentMapper;
import com.muyu.bms.service.StudentService;
import com.muyu.bms.util.BmsUtil;
import com.muyu.bms.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentMapper studentMapper;

	@Autowired
	BorrowMapper borrowMapper;

	BmsUtil bmsUtil = new BmsUtil();
	@Override
	public Student checkLogin(int sid){
		return studentMapper.queryStudentBySid(sid);
	}

	@Override
	public List<Student> queryAllStudent() {
		List<Student> students = studentMapper.queryAllStudent();
		for (Student student : students) {
			if(student!=null) {
				student.setStudentBorrowStatus(bmsUtil.checkOverdueBySid(student.getSid(),borrowMapper.queryBorrowBySid(student.getSid())));
			}
		}
		return students;
	}

	@Override
	public Student queryStudentBySid(int sid) {
		Student student = studentMapper.queryStudentBySid(sid);
		student.setStudentBorrowStatus(bmsUtil.checkOverdueBySid(student.getSid(),borrowMapper.queryBorrowBySid(student.getSid())));
		return student;
	}
}
