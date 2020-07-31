package com.platformmake.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.ConnectMapper;
import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.entity.Connect;
import com.platformmake.model.entity.ConnectExample;
import com.platformmake.model.entity.Equipinfo;
import com.platformmake.model.entity.EquipinfoExample;
import com.platformmake.model.entity.EquipinfoExample.Criteria;

@Service
public class EquipinfoService {

	@Autowired
	private EquipinfoMapper eqMapper;
	
	@Autowired
	private ConnectMapper coMapper;
	
	@Autowired
	private equip_pro con;
	
	
	
	
	
	/**
	 * 增加设备
	 * @param equ
	 * @return true 
	 */
	 public boolean addEquip() {
		 
		 eqMapper.insert(new Equipinfo(0, 20, null) );
		 
		 return true; 
		 
	}
	 
	/**
	 * 设备修改（更新）
	 * @param equ
	 * @return
	 */
	
	public boolean modEquip(Equipinfo equ) {
		int i = eqMapper.updateByPrimaryKeySelective(equ);
		
		return i>0;
	}
	
	/**
	 * 删除设备
	 * @param eqid
	 * @return
	 */
	public boolean delEquip(int eqid) {
		
		int num = eqMapper.deleteByPrimaryKey(eqid);
		
		con.delconnectbyeqid(eqid);
		
		
		return true;
	}
	
	/**
	 * 设备id查询
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Equipinfo> searchEquip(Equipinfo cond,int pageNum,int pageSize){
		EquipinfoExample example = new EquipinfoExample();
		Criteria cc = example.createCriteria();
		if(null != cond.getEqid()) {
			cc.andEqidEqualTo( cond.getEqid() );
		}
		if(null!=cond.getEqstate() ) {
			cc.andEqstateEqualTo( cond.getEqstate() );
		}
		PageHelper.startPage(pageNum, pageSize);
		
		List<Equipinfo> list = eqMapper.selectByExample(example);
		
		return new PageInfo<Equipinfo>(list);
	}
	
	 
}
