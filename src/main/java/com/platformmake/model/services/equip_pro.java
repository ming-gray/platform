package com.platformmake.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.ConnectMapper;
import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.entity.Connect;
import com.platformmake.model.entity.ConnectExample;
import com.platformmake.model.entity.ConnectExample.Criteria;
import com.platformmake.model.entity.DayWorkExample;

@Service
public class equip_pro {

	@Autowired
	private ConnectMapper coMapper;
	
	@Autowired
	private EquipinfoMapper eqmapper;
	/**
	 * 查询产能
	 * @param equip
	 * @param proid
	 * @param count
	 * @return
	 */
	public boolean checkyield(int equip,int proid,int count) {
		ConnectExample example=new ConnectExample();
		Criteria cc=example.createCriteria();
		cc.andEqidEqualTo(equip);
		cc.andProidEqualTo(proid);
		List<Connect>list=coMapper.selectByExample(example);
		if(list.get(0).getYield()<count) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * 根据查询关联
	 * @param cond
	 * @param con
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Connect> searchconnect(Connect cond,Integer pageNum,Integer pageSize){
		ConnectExample example1 = new ConnectExample();
		com.platformmake.model.entity.ConnectExample.Criteria cc2 = example1.createCriteria();
		if(null != cond.getProid()) {
			cc2.andProidEqualTo(cond.getProid());
		}
		if(null != cond.getEqid() ) {
			cc2.andEqidEqualTo( cond.getEqid() );
		}
		
		PageHelper.startPage(pageNum, pageSize);

		List<Connect> list = coMapper.selectByExample(example1);
		
		return new PageInfo<Connect>(list);
	}
	 
	 
	 /**
	  * 删除关联
	  * @param equ
	  * @return
	  */
	 public boolean delconnect(int id) {
		 
		 int i=coMapper.deleteByPrimaryKey(id);
				 
		return i>0;
		 		 
	 }
	 /**
	  * 添加关联
	  * @param equ
	  * @return
	  */
	 public boolean addconntct(int proid,int eqid,int yield) {
		 
		 Connect con=new Connect(0,proid,eqid,yield);
		
		 int i=coMapper.insert(con);
		
		return i>0;
		 		 
	 }
	 
	 
	 /**
	  * 通过设备Id删除关联
	  * @param eqid
	  */
	public void delconnectbyeqid(int eqid) {
		// TODO 自动生成的方法存根
		ConnectExample example = new ConnectExample();
		
		com.platformmake.model.entity.ConnectExample.Criteria cc = example.createCriteria();
		
		cc.andEqidEqualTo(eqid);
		coMapper.deleteByExample(example);
		
	}
	
}
