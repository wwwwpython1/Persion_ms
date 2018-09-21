package com.it.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;


import com.it.dao.IUserDao;
import com.it.entype.User;
import com.it.utils.JNDIUtil;

//持久层实现
public class UserDaoImpl implements IUserDao {
	private QueryRunner qr = new QueryRunner(JNDIUtil.getDataSource());
	@Override
	public User selectUserByInfo(String loginName,String loginPwd) {
		try{
			return qr.query("select * from S_User where loginName=? and loginPwd=? ", new BeanHandler<User>(User.class),loginName,loginPwd);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public int adduser(User user) {
		
		try {
			return qr.update("insert into s_user(userID,userName,loginName,loginPwd,sex,birthday,education,telephone,interest,path,filename,remark)"
					+ " values(?,?,?,?,?,?,?,?,?,?,?,?)",user.getUserID(),user.getUserName(),user.getLoginName(),user.getLoginPwd(),user.getSex(),
					user.getBirthday(),user.getEducation(),user.getTelephone(),user.getInterset(),user.getPath(),user.getFilename(),user.getRamark()
					);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	
	
	/*
	public List<User> selectUserByCondition(String userName, String gender,
			String education, String isUpload) {
		
		//如果上面的所有条件都没选的话
		if(StringUtils.isBlank(userName) && StringUtils.isBlank(gender) && StringUtils.isBlank(education) && StringUtils.isBlank(isUpload)){
			return selectAllUser();
		}else{
			try {
				List<Object> parameters = new ArrayList<Object>();
				String sql = " select * from S_User ";
				StringBuffer ss = new StringBuffer(sql);
				ss.append(" where 1=1 ");
				if(StringUtils.isNotBlank(userName)){
					ss.append(" and userName like ? ");
					parameters.add("%"+userName+"%");
				}
				if(StringUtils.isNotBlank(gender)){
					ss.append(" and gender = ? ");
					parameters.add(gender);
				}
				if(StringUtils.isNotBlank(education)){
					ss.append(" and education = ? ");
					parameters.add(education);
				}
				if(StringUtils.isNotBlank(isUpload)){
					//用户需要使用此条件
					if("true".equals(isUpload)){
						ss.append(" and filename is not null ");//数据库中判断是否为null 不能用!=  =。用的是is not  /is
					}else{
						ss.append(" and filename is null ");
					}
				}
				sql = ss.toString();
				return qr.query(sql, new BeanListHandler<User>(User.class),parameters.toArray());
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

	}
	*/
	/*
	@Override
	public List<User> selectByCondition(String username, String sex, String education) {
		if(StringUtils.isBlank(username)&&StringUtils.isBlank(sex)&&StringUtils.isBlank(education)){
			return selectAllUser();
		}
		List<Object> para = new ArrayList<Object>();
		String sql ="select *from s_user";
		StringBuffer sb =new StringBuffer(sql);
		sb.append("where 1=1");
		if(StringUtils.isNotBlank(username)){
			sb.append("and userName like ?");
			para.add("%"+username+"%");
		}if(StringUtils.isNotBlank(sex)){
			sb.append("and sex =?");
			para.add(sex);
		}if(StringUtils.isNotBlank(education)){
			sb.append("and education=?");
			para.add(education);
		}
		/*if(StringUtils.isNotBlank(isUpload)){
			if("true".equals(isUpload)){
				sb.append("and filename is not null ");
			}else{
				sb.append("and filename  is null");
			}
		}
		sql = sb.toString();
		try {
			return (List<User>) qr.query(sql, new BeanHandler<User>(User.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
		
	}*/
	
	public User selectUserByID(Integer userID) {
		try {
			return qr.query("select *from s_user where userID=? ", new BeanHandler<User>(User.class),userID);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public User selectUserByName(String userName) {
		try {
			return qr.query("select *from s_user where userName=? ", new BeanHandler<User>(User.class),userName);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public List<User> selectAllUser() {
		try{
			return qr.query("select * from S_User ", new BeanListHandler<User>(User.class));
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	@Override
	public int updateUser(User user) {
		try {
			return qr.update("update s_user set userName=?,loginName=?,loginPwd=?,sex=?,birthday=?,education=?,telephone=?,interest=?,path=?,filename=?,remark=? where userID=?", user.getUserName(),user.getLoginName(),user.getLoginPwd(),user.getSex(),
					user.getBirthday(),user.getEducation(),user.getTelephone(),user.getInterset(),user.getPath(),user.getFilename(),user.getRamark(),user.getUserID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	@Override
	public int delete(Integer userID) {
		try {
			return qr.update("delete  from s_user where userID=?",userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}
	/*
	@Override
	public List<User> selectByCondition(String username, String sex, String education) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<User> selectUserByCondition(String userName, String gender, String education, String isUpload) {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	
}
