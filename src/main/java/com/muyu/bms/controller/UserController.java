package com.muyu.bms.controller;

import com.muyu.bms.service.UserService;
import com.muyu.bms.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	//转到用户管理页面
	@RequestMapping("/toUserManagementPage")
	public String toUserManagementPage(Model model){
		model.addAttribute("users",userService.queryAllUser());
		return "userManagement";
	}

	//转到用户添加页面
	@RequestMapping("/toAddUserPage")
	public String toAddUserPage(){
		return "user/addUser";
	}

	//添加用户
	@PostMapping("user")
	public String addUser(User user){
		if(userService.addUser(user)){
			return "redirect:toUserManagementPage";
		}else{
			return "user/add_user";
		}
	}

	//转到修改用户页面
	@RequestMapping("/toModificationUserPage")
	public String toModificationUserPage(@RequestParam("id") int id,
										 Model model){
		User user = userService.queryUserById(id);
		model.addAttribute("user",user);
		return "user/modificationUser";
	}

	//修改用户
	@RequestMapping("/modificationUser")
	public String modificationUser(User user){
		if(userService.updateUser(user)){
			return "redirect:toUserManagementPage";
		}
		return "user/modificationUser";
	}

	//删除用户
	@RequestMapping("/deleteUser")
	public String deleteUser(Integer id){
		if(userService.disableUser(id)){
			return "redirect:toUserManagementPage";
		}
		return "redirect:toUserManagementPage";
	}

	//检查用户是否重复
	@RequestMapping("/checkUserRepetitive")
	@ResponseBody
	public Boolean checkUserRepetitive(String username){
		if(userService.checkUserRepetitive(username)){
			return true;
		}
		return false;
	}
}
