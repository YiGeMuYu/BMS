package com.muyu.bms.mapper;

import com.muyu.bms.vo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
	List<User> queryAllUser();

	User queryUserById(@Param("id") int id);

	User queryUserByUsername(@Param("username") String username);

	User queryUserByUsernameAndPassword(@Param("username") String username , @Param("password") String password);

	int addUser(@Param("User") User user);

	int updateUser(@Param("user") User user);

	int updateUserRank(@Param("id") int id);

	int deleteUser(int id);
}
