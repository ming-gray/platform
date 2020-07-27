package com.platformmake.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformmake.model.dao.ConnectMapper;
import com.platformmake.model.entity.Connect;
import com.platformmake.model.entity.ConnectExample;
import com.platformmake.model.entity.ConnectExample.Criteria;
import com.platformmake.model.entity.DayWorkExample;

@Service
public class equip_pro {

	@Autowired
	private ConnectMapper eqpromapper;
	
	public boolean checkyield(int equip,int proid,int count) {
		ConnectExample example=new ConnectExample();
		Criteria cc=example.createCriteria();
		cc.andEqidEqualTo(equip);
		cc.andProidEqualTo(proid);
		List<Connect>list=eqpromapper.selectByExample(example);
		if(list.get(0).getYield()<count) {
			return false;
		}
		
		return true;
	}
}
