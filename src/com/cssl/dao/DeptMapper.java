package com.cssl.dao;

import java.util.List;
import java.util.Map;

import com.cssl.pojo.Dept;

public interface DeptMapper {
	
	public int insert(Dept dept);
	
	public List<Dept> select();
	

}
