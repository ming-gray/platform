package com.platformmake.model.services;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.Workinfo;

/**
  *  生产调度services测试类
 * @author ming
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestWorkinfoService {

	@Autowired
	private WorkinfoService service;
	
	@Test
	public void testAddWorkinfo() {
		boolean b = service.addWorkinfo(new Workinfo(0, null, null, null, 10, 1, 1, 1, null, null));
		System.out.println(b);
	}
	
	@Test
	public void testStartWorkinfo() {
		Workinfo w = service.startWorkinfo(3);
		System.out.println(w);
	}
	
	@Test
	public void testSelectByWorkid() {
		Workinfo workinfo = service.selectByWorkid(1);
		System.out.println(workinfo);
	}
	
	@Test
	public void testDeleteById() {
		boolean b = service.deleteById(3);
		System.out.println(b);
	}
	
	@Test
	public void testListWorkinfo() {
		PageInfo<Workinfo> list = service.listWorkinfo(new Workinfo(2, null, null, null, null, null, null, null, null, null), 1, 3);
		System.out.println(list);
		for(Workinfo workinfo: list.getList()) {
			System.out.println(workinfo);
		}
	}
	
	@Test
	public void testUpdateWorkinfo() {
		boolean b = service.updateWorkinfo(new Workinfo(2, null, null, null, 20, null, null, null, null, null));
		System.out.println(b);
	}
	
	@Test
	public void testSelectByPlanid() {
		List<Workinfo> list = service.selectByPlanid(1);
		System.out.println(list);
	}
}