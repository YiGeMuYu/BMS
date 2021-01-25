package com.muyu.bms.controller;

import com.muyu.bms.service.StudentService;
import com.muyu.bms.service.UserService;
import com.muyu.bms.vo.Student;
import com.muyu.bms.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	UserService userService;

	@Autowired
	StudentService studentService;
	/**
	 * 登录
	 * 验证用户名和密码
	 * 如果正确则进入index.html页面 并向session中传入用户名的值
	 * 如果错误则返回login,html页面 并用request携带loginErrMsg
	 * @param username
	 * @param password
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/checkLogin")
	public String checkLogin(@RequestParam("username") String username ,
						@RequestParam("password") String password ,
						Model model ,
						HttpSession session){
		if(userService.checkLogin(username,password)){
			session.setAttribute("loginSession",username);
			session.setAttribute("rank","manager");
			return "redirect:queryAllBookToIndex";
		}else{
			model.addAttribute("loginErrMsg","用户名或密码错误!");
			return "login";
		}

	}

	@RequestMapping("checkLoginStudent")
	public String checkLoginStudent(@RequestParam("sid") int sid,
									HttpSession session,
									Model model){
		Student student = studentService.checkLogin(sid);
		if(student!=null){
			session.setAttribute("loginSession",student.getSname());
			session.setAttribute("rank","student");
			session.setAttribute("sid",sid);
			return "redirect:queryAllBookToIndex";
		}
		model.addAttribute("loginErrMsg","学号错误!");
		return "login";
	}

}
