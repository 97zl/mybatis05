package com.cssl.dao;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Emp;

public interface EmpMapper {
	
	public int insert(Emp emp);
	
	public int update(Emp emp);

	public List<Emp> select();	
	
	public Emp selectById(int empno);
	
	public void page(Map<String, Object> map);
	
}
