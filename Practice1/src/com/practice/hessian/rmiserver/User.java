package com.practice.hessian.rmiserver;

import java.io.Serializable;

public class User implements Serializable{
//	序列化操作的时候系统会把当前类的serialVersionUID写入到序列化文件中，当反序列化时系统会去检测文件中的serialVersionUID，
//	判断它是否与当前类的serialVersionUID一致，如果一致就说明序列化类的版本与当前类版本是一样的，可以反序列化成功，否则失败。
//	private static final long serialVersionUID = 1L;默认的ID
	private static final long serialVersionUID = -6240738527213257448L;//生成的ID
	
	private String userName;
	private String userPassword;
	private int userId;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString(){
		return userName+"-"+userPassword+"-"+userId;
	}
}
