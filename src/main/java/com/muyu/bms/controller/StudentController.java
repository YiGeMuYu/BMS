package com.muyu.bms.controller;

import com.muyu.bms.service.BorrowService;
import com.muyu.bms.service.StudentService;
import com.muyu.bms.vo.Borrow;
import com.muyu.bms.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {

	@Autowired
	StudentService studentService;
	@Autowired
	BorrowService borrowService;

	@RequestMapping("/queryAllStudentToManagement")
	public String queryAllStudentToManagement(Model model){
		List<Student> students = studentService.queryAllStudent();
		model.addAttribute("students",students);
		return "user/studentManagement";
	}

	@RequestMapping("toStudentDetailPage")
	public String toStudentDetailPage(@RequestParam("sid") Integer sid,Model model){
		Student student = studentService.queryStudentBySid(sid);
		List<Borrow> borrows = borrowService.queryBorrowBookListBySid(sid);
		model.addAttribute("student",student);
		model.addAttribute("borrows",borrows);
		return "user/studentDetail";
	}
}
