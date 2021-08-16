package com.cssl.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.cssl.controller.StudentServlet;

/**
 * 工具类
 * @author TYM
 *
 */
public class MyBatisUtils {
	
	private static SqlSessionFactory factory;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	
	static {		
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			factory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {			
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	
	/**
	 * 同一线程获得同一session
	 * @return
	 */
	public static SqlSession getSession() {
			
		SqlSession session = threadLocal.get();	//从自己的线程中获取对应session
		if(session == null) {
			session = getFactory().openSession();
			threadLocal.set(session);
		}
		System.out.println("MyBatisUtils:"+session);
		return session;
	}
	
	public static void close() {
		SqlSession session = threadLocal.get();
		if(session != null) {
			session.close();
			session = null;
		}
		threadLocal.set(null);
	}

	public static void main(String[] args) {
		//SqlSession session1 = MyBatisUtils.getFactory().openSession();
		//SqlSession session2 = MyBatisUtils.getFactory().openSession();
		
		SqlSession session1 = getSession();
		SqlSession session2 = getSession();
		
		System.out.println(session1 == session2);
		
		StudentServlet ss = new StudentServlet();
		ss.doGet();
	}
}
