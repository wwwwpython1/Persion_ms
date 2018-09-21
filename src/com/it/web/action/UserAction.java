package com.it.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.TokenHelper;

import com.it.entype.User;
import com.it.service.IUserService;
import com.it.service.impl.UserServiceImpl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;


public class UserAction  extends ActionSupport  implements ModelDriven<User>{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	//private IUserService service = new UserServiceImpl();
	private IUserService service = new UserServiceImpl();
	User user = new User();
	private File upload ;
	private List<User> users ;
	private InputStream inputStream;
	private String oldFileName;
	private String isUpload;
	private String uploadFileName; 
	
	public String getIsUpload() {
		return isUpload;
	}
	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}
	
	public String getOldFileName() {
		return oldFileName;
	}
	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public File getUpload() {
		return upload;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getModel() {
		return user;
	}
	
	
	public String login(){
		User dbUser = service.login(user.getLoginName(), user.getLoginPwd());
		if(dbUser==null){
			addActionError("用户名不存在");
			return "input";
		}
		HttpSession session =ServletActionContext.getRequest().getSession();
		session.setAttribute("user",dbUser);
		return SUCCESS;
	}
	
	public String findAll(){
		users = service.findAllUSer();
		return SUCCESS;
	}
	
	/*public String findUserByCondition()throws Exception{
		users = service.findByCondition(user.getUserName(), user.getSex(), user.getEducation());
		if(users!=null){
			System.out.print("查询成功");
		}
		return SUCCESS;
	}*/
	
	
	public String findUserByName(){
		user  = service.findUserByName(user.getUserName());
		ValueStack vs= ActionContext.getContext().getValueStack();
		vs.push(user);
		return SUCCESS;
	}
	
	
	public String findUserById(){
		user  = service.findUserByID(user.getUserID());
		ValueStack vs= ActionContext.getContext().getValueStack();
		vs.push(user);
		return SUCCESS;
	}
	
	public String add()throws Exception{
		String filePath = ServletActionContext.getServletContext().getRealPath("/files");
		String dir = generateChildPath(filePath);
		String fileName = TokenHelper.generateGUID()+"_"+uploadFileName;//DSK32JJKSDF2LKDSFSDFJK_uploadFileName;
		user.setPath(dir);
		user.setFilename(fileName);
		upload.renameTo(new File(filePath+File.separator+dir,fileName));
		int res = service.saveUser(user);
		if(res > 0){
			return SUCCESS;
		}
		return null;
	}
	
	
	private String generateChildPath(String filepath) {
		Date date  =new Date();
		DateFormat df =new SimpleDateFormat("yyyy-MM-dd");
		String dir  = df.format(date);
		File file = new File(filepath,dir);
		if(!file.exists()){
			file.mkdirs();
		}
		return dir;
	}
	
	
	
	
	public String download() throws Exception{
		User dbUser = service.findUserByID(user.getUserID());
		String filePath = ServletActionContext.getServletContext().getRealPath("/files");
		oldFileName = dbUser.getFilename().substring(dbUser.getFilename().indexOf("_")+1);
		inputStream = new FileInputStream(filePath+File.separator+dbUser.getPath()+File.separator+dbUser.getFilename());
		return SUCCESS;
	}
	
	
	
	
	public String delete(){
		int res= service.removeUser(user.getUserID());
		if(res>0){
			System.out.print("删除成功！");
			return SUCCESS;
		}
		return null;
	}
	
	public String editui(){
		user  = service.findUserByID(user.getUserID());
		ValueStack vs= ActionContext.getContext().getValueStack();
		vs.push(user);
		return SUCCESS;
	}
	
}
