package com.cssl.test;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cssl.dao.StudentMapper;
import com.cssl.pojo.Student;
import com.cssl.util.MyBatisUtils;

class JTest {
	
	private static SqlSessionFactory factory;
	private SqlSession session;
	private StudentMapper stuMapper;

	/**
	 * 测试之前执行一次
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("@BeforeAll");
		factory = MyBatisUtils.getFactory();
	}

	/**
	 * 每个测试方法前执行
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {		
		session = factory.openSession();
		stuMapper = session.getMapper(StudentMapper.class);
	}
	
	@Test
	void testInsert() {
		System.out.println("insert");
		Student stu = new Student();
		stu.setSname("运杰");
		stu.setBorndate(new Date());		
	
		int count = stuMapper.insert(stu);		
		System.out.println(count+"\tsid:"+stu.getSid());
	}
	
	@Test
	void testInsertBach() {
		
		Student stu1 = new Student();
		stu1.setSname("运杰1");
		stu1.setBorndate(new Date());	
		
		Student stu2 = new Student();
		stu2.setSname("运杰2");
		stu2.setBorndate(new Date());	
	
		int count = stuMapper.insertBach(Arrays.asList(stu1,stu2));		
		System.out.println(count);
	}
	
	@Test
	void testUpdate() {
		Student stu = new Student();
		//stu.setSid(4);
		stu.setSname("杰哥");
		stuMapper.update(stu);
	}
	
	@Test
	void testDelete() {
		Student stu = new Student();
		stuMapper.delete(stu);
	}
	
	@Test
	void testSelectById() {
		
		List<Student> list = stuMapper.selectById(Arrays.asList(2,4,6,9));
		list.forEach(System.out::println);
	}
	
	@Test
	void testSelectAll() {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("sid", 2);
		//param.put("sname", "哥");
		param.put("borndate", new Date());
		param.put("column", "borndate");
		param.put("ord", "asc");
		param.put("first", 3);
		param.put("size", 3);
		List<Student> list = stuMapper.select(param);
		list.forEach(System.out::println);
	}
	
	
	@Test
	void testSelectLike() {
		Student stu = new Student();
		stu.setSname("哥");
		List<Student> list = stuMapper.selectLike(stu);
		list.forEach(System.out::println);
	}
	

	@AfterEach
	void tearDown() throws Exception {		
		session.commit();
		session.close();
	}

	
}
