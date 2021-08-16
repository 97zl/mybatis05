package com.cssl.service.impl;

import org.apache.ibatis.session.SqlSession;

import com.cssl.dao.StudentMapper;
import com.cssl.pojo.Student;
import com.cssl.service.StudentService;
import com.cssl.util.MyBatisUtils;

public class StudentServiceImpl implements StudentService {
	
	private StudentMapper stuMapper;
	private SqlSession session;	
	
	private void init() {
		session = MyBatisUtils.getSession();
		System.out.println("service:"+session);
		stuMapper = session.getMapper(StudentMapper.class);
	}

	@Override
	public int save(Student stu) {
		init();	
		return 1;
		//return stuMapper.insert(stu);
	}

	@Override
	public int delete(int sid) {
		init();			
		return stuMapper.delete(null);
	}

}
