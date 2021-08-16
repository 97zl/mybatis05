package com.cssl.service;

import com.cssl.pojo.Student;

public interface StudentService {
	
	public int save(Student stu);
	
	public int delete(int sid);

}
