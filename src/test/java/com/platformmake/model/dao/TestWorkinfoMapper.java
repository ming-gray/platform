package com.platformmake.model.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.platformmake.model.entity.Workinfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestWorkinfoMapper {

	@Autowired
	private WorkinfoMapper wm;
	
	@Test
	public void testselectByPrimaryKey() {
		Workinfo workinfo = wm.selectByPrimaryKey(1);
		System.out.println(workinfo);
	}
}
