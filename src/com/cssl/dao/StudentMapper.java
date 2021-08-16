package com.cssl.dao;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Student;

public interface StudentMapper {
		
	public int insert(Student stu);	
	
	public int insertBach(List<Student> stus);
	
	public int update(Student stu);
	
	public int delete(Student stu);
	
	public List<Student> selectById(List<Integer> ids);
	
	public List<Student> select(Map<String, Object> param);
	
	public List<Student> selectLike(Student stu);
}
