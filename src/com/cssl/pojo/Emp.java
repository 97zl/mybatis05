package com.cssl.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Setter
@Getter
@ToString
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(of = "empno")
public class Emp implements Serializable {
	
	
	private Integer empno;
	@NonNull
	private String ename;
	@NonNull
	private Integer sal;
	@NonNull
	private Date hiredate;
	private Integer dept_no;
	//多对一
	@NonNull
	private Dept dept;
	
	
	
}
