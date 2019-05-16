package com.hessian.rmiclient;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hessian.rmihessian.IMyHessionService;

public class MyHessianClient {

	public static void main(String[] args) {
		String url = "http://127.0.0.1:9114/practice1/userInfo";
		HessianProxyFactory factory = new HessianProxyFactory();
		IMyHessionService service;
		try {
			service = (IMyHessionService) factory.create(IMyHessionService.class, url);
			String  result = service.operate("20190514");
			System.out.println("hessian return ->"+result);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
