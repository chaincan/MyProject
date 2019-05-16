package com.hessian.rmiserver;

import com.hessian.rmihessian.IMyHessianService;

public class MyHessianServiceImp implements IMyHessianService{

	@Override
	public String operate(String date) {
		User user = new User();
		user.setUserName("root_"+date);
		user.setUserPassword("root110");
		user.setUserId(1);
		return user.toString();
	}

	public static void main(String[] args){
		System.out.println(MyHessianServiceImp.class.getName());
	}
}
