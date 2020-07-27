package com.platformmake.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformmake.model.dao.DayWorkMapper;
import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.dao.OrderinfoMapper;
import com.platformmake.model.dao.ProductinfoMapper;
import com.platformmake.model.dao.TrackinfoMapper;
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.entity.ProductinfoExample;
import com.platformmake.model.entity.ProductinfoExample.Criteria;

@Service
public class productService {

	@Autowired
	private ProductinfoMapper promapper;
	
	@Autowired
	private EquipinfoMapper eqmapper;
	
	@Autowired
	private OrderinfoMapper ordmapper;
	
	@Autowired
	private TrackinfoMapper trackmapper;
	
	@Autowired
	private WorkinfoMapper workmapper;
	
	@Autowired
	private DayWorkMapper daymapper;
	
	
	/**
	 * 产品名找产品ID
	 */

	public Productinfo dosearchproid(String proname) {
		
		ProductinfoExample example=new ProductinfoExample();
		Criteria cc=example.createCriteria();
		cc.andPronameEqualTo(proname);
		List<Productinfo>list=promapper.selectByExample(example);
		return list.get(0);
		
	}
}
