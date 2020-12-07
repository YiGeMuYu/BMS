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
	@RequestMapping("/toUserManagementPage")
	public String toUserManagementPage(Model model){
		model.addAttribute("users",userService.queryAllUser());
		return "user_management";
	}

	@RequestMapping("/toAddUserPage")
	public String toAddUserPage(){
		return "user/add_user";
	}

	@PostMapping("user")
	public String addUser(User user){
		if(userService.addUser(user)){
			return "redirect:toUserManagementPage";
		}else{
			return "user/add_user";
		}
	}

	@RequestMapping("/toModificationUserPage")
	public String toModificationUserPage(@RequestParam("id") int id,
										 Model model){
		User user = userService.queryUserById(id);
		model.addAttribute("user",user);
		return "user/modification_user";
	}

	@RequestMapping("/modificationUser")
	public String modificationUser(User user){
		if(userService.updateUser(user)){
			return "redirect:toUserManagementPage";
		}
		return "user/modification_user";
	}

	@RequestMapping("/deleteUser")
	public String deleteUser(Integer id){
		if(userService.disableUser(id)){
			return "redirect:toUserManagementPage";
		}
		return "redirect:toUserManagementPage";
	}

	@RequestMapping("/checkUserRepetitive")
	@ResponseBody
	public Boolean checkUserRepetitive(String username){
		if(userService.checkUserRepetitive(username)){
			return true;
		}
		return false;
	}
}
