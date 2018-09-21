package com.it.service;
/*
 * 用户相关的业务层
 * 
 */

import java.util.List;
import com.it.entype.User;
public interface IUserService {
	User login(String loginName,String loginPwd);
	int saveUser(User user);
	int modifyUser(User user);
	int removeUser(Integer userID);
	User findUserByID(Integer userID);
	User findUserByName(String userName); 
	List<User> findAllUSer();
	//List<User> findByCondition(String username,String sex,String education );
	//List<User> findUserByCondition(String userName,String gender,String education,String isUpload);
}