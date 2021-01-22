package com.muyu.bms.service.impl;

import com.muyu.bms.mapper.UserMapper;
import com.muyu.bms.service.UserService;
import com.muyu.bms.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

	@Override
	public boolean checkLogin(String username, String password) {
		User user = userMapper.queryUserByUsernameAndPassword(username, password);
		if(user!=null || user instanceof com.muyu.bms.vo.User){
			return true;
		}
		return false;
	}

	@Override
	public List<User> queryAllUser() {
		List<User> users = userMapper.queryAllUser();
		return users;
	}

	@Override
	public boolean addUser(User user) {
		int i = userMapper.addUser(user);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public User queryUserById(int id) {
		return userMapper.queryUserById(id);
	}

	@Override
	public boolean updateUser(User user) {
		int i = userMapper.updateUser(user);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean disableUser(int id) {
		int i = userMapper.updateUserRank(id);
		if(i>0){
			return true;
		}
		return false;
	}

	@Override
	public boolean checkUserRepetitive(String username) {
		User user = userMapper.queryUserByUsername(username);
		if(user!=null){
			return true;
		}
		return false;
	}


}
