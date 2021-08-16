package com.cssl.controller;

import com.cssl.service.StudentService;
import com.cssl.service.impl.StudentServiceImpl;
import com.cssl.util.Transaction;

/**
 * 单例模式
 * @author TYM
 *
 */
public class StudentServlet {
	
	private StudentService service ;
	
	public void doGet() {
		
		service = new StudentServiceImpl();
		Transaction tran = new Transaction(service);
		
		service = (StudentService)tran.getProxy();
		service.save(null);
		
	}

	//session is closed 
}
