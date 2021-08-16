package com.cssl.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.apache.ibatis.session.SqlSession;

/**
 * 事务代理
 * @author TYM
 *
 */
public class Transaction implements InvocationHandler {
	
	private Object target;
	
	public Transaction(Object target) {
		super();
		this.target = target;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		SqlSession session = MyBatisUtils.getSession();
		System.out.println("invoke:"+session);
		Object r = null;;
		try {
			r = method.invoke(target, args);
			session.commit();			
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			MyBatisUtils.close();
		}
		return r;

	}
	
	
	public Object getProxy() {
		return Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), this);
	}

}
