package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.StudentMapper;
import com.muyu.bms.service.StudentService;
import com.muyu.bms.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentMapper studentMapper;

	@Override
	public Student checkLogin(int sid) {

		return studentMapper.queryStudentBySid(sid);
	}
}
