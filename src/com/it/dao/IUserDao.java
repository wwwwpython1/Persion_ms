package com.it.dao;

import java.util.List;

import com.it.entype.User;

//³Ö¾Ã²ã½Ó¿Ú
public interface IUserDao {

	User selectUserByInfo(String loginName, String loginPwd);

	int adduser(User user);

	//List<User> selectByCondition(String username, String sex, String education);

	User selectUserByID(Integer userID);
	User selectUserByName(String userName); 
	//List<User> selectUserByCondition(String userName, String gender,String education, String isUpload);
	List<User> selectAllUser();

	int updateUser(User user);

	int delete(Integer userID);

}
