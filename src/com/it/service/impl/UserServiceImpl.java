package com.it.service.impl;

import java.util.List;

import com.it.dao.IUserDao;
import com.it.dao.impl.UserDaoImpl;
import com.it.entype.User;
import com.it.service.IUserService;

//用户相关的业务层
public class UserServiceImpl implements IUserService {

	private IUserDao dao = new UserDaoImpl();
	@Override
	public User login(String loginName, String loginPwd) {
		// TODO Auto-generated method stub
		return dao.selectUserByInfo( loginName, loginPwd);
	}

	@Override
	public int saveUser(User user) {
		// TODO Auto-generated method stub
		return dao.adduser(user);
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		return dao.updateUser(user);
	}

	@Override
	public int removeUser(Integer userID) {
		// TODO Auto-generated method stub
		return dao.delete(userID);
	}

	@Override
	public User findUserByID(Integer userID) {
		// TODO Auto-generated method stub
		return dao.selectUserByID(userID);
	}

	@Override
	public List<User> findAllUSer() {
		// TODO Auto-generated method stub
		return dao.selectAllUser();
	}
	
	/*
	@Override
	public List<User> findByCondition(String username, String sex, String education) {
		// TODO Auto-generated method stub
		return dao.selectByCondition(username,sex,education);
	}*/

	@Override
	public User findUserByName(String userName) {
		// TODO Auto-generated method stub
		return dao.selectUserByName(userName);
	} 
  /*
	@Override
	public List<User> findUserByCondition(String userName, String gender, String education, String isUpload) {
		// TODO Auto-generated method stub
		return dao.selectUserByCondition(userName, gender, education, isUpload);
		
	}
	*/

}
