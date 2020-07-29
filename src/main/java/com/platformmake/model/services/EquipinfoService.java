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
	
	/**
	 * 设备修改
	 * @param equ
	 * @return
	 */
	public boolean modEquip(Equipinfo equ,Connect con) {
		EquipinfoExample example = new EquipinfoExample();
		Criteria cc = example.createCriteria();
		
		
		ConnectExample example1 = new ConnectExample();
		com.platformmake.model.entity.ConnectExample.Criteria cc2 = example1.createCriteria();
		/*cc.andEqidEqualTo(equ.getEqid());
		List<Equipinfo> list = eqMapper.selectByExample(example);
		if(list.size() > 0) {
			return false;
		}
		*/
		int i = eqMapper.updateByPrimaryKeySelective(equ), j = coMapper.updateByPrimaryKeySelective(con);
		
		return i*j>0;
	}
	/**
	 * 删除设备
	 * @param eqid
	 * @return
	 */
	public boolean delEquip(int eqid) {
		
		int num = eqMapper.deleteByPrimaryKey(eqid);
		int num2 = coMapper.deleteByPrimaryKey(eqid);
		return (num > 0)&&(num2 > 0);
	}
	
	/**
	 * 设备id查询
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Equipinfo> searchEquip(Equipinfo cond,Connect con,Integer pageNum,Integer pageSize){
		EquipinfoExample example = new EquipinfoExample();
		ConnectExample example1 = new ConnectExample();
		Criteria cc = example.createCriteria();
		com.platformmake.model.entity.ConnectExample.Criteria cc2 = example1.createCriteria();
		if(null != cond.getEqid()) {
			cc.andEqidEqualTo(cond.getEqid());
		}
		if(null != con.getEqid()) {
			cc2.andEqidEqualTo(con.getEqid());
		}
		PageHelper.startPage(pageNum, pageSize);
		List<Equipinfo> list = eqMapper.selectByExample(example);
		List<Connect> list2 = coMapper.selectByExample(example1);
		//把两个list加在一起
		List list3 = new ArrayList();
		list3.addAll(list);
		list3.addAll(list2);
		
		return new PageInfo<Equipinfo>(list3);
	}
	/**
	 * 根据产品id查找
	 * @param cond
	 * @param con
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Connect> searchEquipByProid(Connect con,Integer pageNum,Integer pageSize){
		ConnectExample example1 = new ConnectExample();
		com.platformmake.model.entity.ConnectExample.Criteria cc2 = example1.createCriteria();
		if(null != con.getProid()) {
			cc2.andProidEqualTo(con.getProid());
		}
		PageHelper.startPage(pageNum, pageSize);

		List<Connect> list2 = coMapper.selectByExample(example1);
		
		return new PageInfo<Connect>(list2);
	}
	/**
	 * 增加设备
	 * @param equ
	 * @return true 
	 */
	 public boolean addEquip(Equipinfo equ,Connect con) {
		EquipinfoExample example = new EquipinfoExample();
		Criteria cc = example.createCriteria();
		cc.andEqidEqualTo(equ.getEqid());
		List<Equipinfo> list = eqMapper.selectByExample(example);
		
		ConnectExample example1 = new ConnectExample();
		com.platformmake.model.entity.ConnectExample.Criteria cc2 = example1.createCriteria();
		cc2.andEqidEqualTo(con.getId());
		List<Connect> list2 = coMapper.selectByExample(example1);
		
		List list3 = new ArrayList();
		list3.addAll(list);
		list3.addAll(list2);
		
		if(list3.size() > 0){
			return false;
		}
		eqMapper.insert(equ);
		coMapper.insert(con);
		 return true;
		 /*
		 * boolean isOK = checkEquipid(.getEqid()); if(!isOK) { return false; } //2.
		 * 锟斤拷锟叫诧拷锟斤拷锟斤拷锟� eqMapper.insert(equ);
		 * 
		 * return true;
		 */
		 
	}
	 
	 
	 
	/*
	 * public boolean checkEquipid(String eqid) { //1. 锟斤拷锟斤拷突 EquipinfoExample example
	 * = new EquipinfoExample();
	 * com.platformmake.model.entity.EquipinfoExample.Criteria cc =
	 * example.createCriteria(); //锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷锟� cc.andEquipidEqualTo(equ.getEqid()); List
	 * <Equipinfo> list = eqMapper.selectByExample(example);
	 * 
	 * return list.size() == 0; }
	 */
	 
	 /**
	  * 锟斤拷证锟斤拷锟斤拷
	  * @param equ
	  * @return
	  */
	 public Equipinfo checkLogin(Equipinfo equ) {
		 
		 EquipinfoExample example = new EquipinfoExample();
		 Criteria cc = example.createCriteria();
		 
		 cc.andEqidEqualTo(equ.getEqid());
		 cc.andEqstateEqualTo(equ.getEqstate());
		 
		 List<Equipinfo> list = eqMapper.selectByExample(example);
		 
		 //锟叫讹拷锟斤拷没锟斤拷
		 if(list.size() > 0) {
			 return list.get(0);
		 }
		 else {
			return null;
		 }
		 		 
	 }
}
