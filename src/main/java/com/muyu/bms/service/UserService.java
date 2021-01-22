package com.muyu.bms.service;

import com.muyu.bms.vo.User;;

import java.util.List;

public interface UserService {

	boolean checkLogin(String username ,String password);

	List<User> queryAllUser();

	boolean addUser(User user);

	User queryUserById(int id);

	boolean updateUser(User user);

	boolean disableUser(int id);

	boolean checkUserRepetitive(String username);
}
