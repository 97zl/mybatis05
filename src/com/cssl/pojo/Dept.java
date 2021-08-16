package com.cssl.pojo;

import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Dept {
	
	private Integer deptno;
	@NonNull
	private String dname;
	@NonNull
	private String loc;
	//一对多	
	private Set<Emp> emps;	


}
