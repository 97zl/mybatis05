package com.cssl.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.cssl.dao.DeptMapper;
import com.cssl.dao.EmpMapper;
import com.cssl.pojo.Dept;
import com.cssl.pojo.Emp;
import com.cssl.util.MyBatisUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JTest2 {
	
	private static SqlSessionFactory factory;
	private SqlSession session;
	private DeptMapper deptMapper;
	private EmpMapper empMapper;
	
	/**
	 * 测试之前执行一次
	 * @throws Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {		
		factory = MyBatisUtils.getFactory();
	}

	/**
	 * 每个测试方法前执行
	 * @throws Exception
	 */
	@BeforeEach
	void setUp() throws Exception {		
		session = factory.openSession();
		deptMapper = session.getMapper(DeptMapper.class);
		empMapper = session.getMapper(EmpMapper.class);	
	}
	
		
	@Order(1)
	@Test
	void testInsert() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Dept dept = new Dept("测试部","长沙"); 
		Emp e1 = new Emp("世红",8888,sdf.parse("2020-12-12"),dept);
		Emp e2 = new Emp("军哥",6666,new Date(),dept);
		
		//e1.setEmpno(1).setEname("").setDept(null);
		
		deptMapper.insert(dept);
		empMapper.insert(e1);
		empMapper.insert(e2);
	}
	
	/**
	 * 多对一
	 */
	@Order(2)
	@Test
	void testSelectEmp() {
		Page<Emp> page = PageHelper.offsetPage(10, 5);
		//Page<Emp> page = PageHelper.startPage(2, 5, true);
		//Page<Emp> page = PageHelper.startPage(20, 5, "empno desc");
		empMapper.select();
		
		System.out.println("total:"+page.getTotal());
		System.out.println("pages:"+page.getPages());
		System.out.println("num:"+page.getPageNum());
		
		List<Emp> list = page.getResult();
		list.forEach(e->{
			System.out.println(e.getEmpno()+"\t"+e.getEname()+"\t"+e.getSal());			
		});
		
		//String json = "{\"list\":"+list+"}";
		//System.out.println(json);
		/*String json = JSON.toJSONString(list,
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.PrettyFormat);*/
		
		/*String json = JSON.toJSONStringWithDateFormat(
				list,"yyyy-MM-dd",
				SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.PrettyFormat);
		
		System.out.println(json);*/
	}
	
	@Order(2)
	@Test
	void testSelectPage() {
		
		PageHelper.startPage(1, 1);
		//deptMapper.select();
		
		List<Emp> list = empMapper.select();
		PageInfo<Emp> page = new PageInfo<Emp>(list,5);
		
		System.out.println("total:"+page.getTotal());
		System.out.println("pages:"+page.getPages());
		System.out.println("num:"+page.getPageNum());
		
		list = page.getList();
		list.forEach(e->{
			System.out.println(e.getEmpno()+"\t"+e.getEname()+"\t"+e.getSal());			
		});		
		
		int[] nums = page.getNavigatepageNums();
		for (int i : nums) {
			System.out.print(i+"\t");
		}
		System.out.println("\n"+page.getNavigateFirstPage());
	}
	
	@Test
	void testPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pindex", 1);
		map.put("psize", 5);
		map.put("rs", new ArrayList<HashMap<String, Object>>());
		empMapper.page(map);
		
		List<HashMap<String, Object>> list = (List<HashMap<String, Object>>)map.get("rs");
		list.forEach(System.out::println);
	}
	
	@AfterEach
	void tearDown() throws Exception {		
		session.commit();
		session.close();
	}


}
