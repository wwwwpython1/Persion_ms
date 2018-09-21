package com.it.tst;

import com.it.dao.IUserDao;
import com.it.dao.impl.UserDaoImpl;
import com.it.entype.User;
import com.it.service.IUserService;
import com.it.service.impl.UserServiceImpl;

public class test {
	
	
	
	public static void main(String[] args) {
		 IUserService serService = new UserServiceImpl();
		 User user = serService.login("admin","admin");
		 System.out.print(user.getUserName());
		
	}

}
