package com.platformmake.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.OrderinfoMapper;
import com.platformmake.model.dao.PlaninfoMapper;
import com.platformmake.model.dao.ProductinfoMapper;
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.Orderinfo;
import com.platformmake.model.entity.OrderinfoExample;
import com.platformmake.model.entity.Planinfo;
import com.platformmake.model.entity.PlaninfoExample;
import com.platformmake.model.entity.PlaninfoExample.Criteria;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.entity.Workinfo;

/**
 * 生产计划管理模块
 * @author Administrator
 *
 */
@Service
public class PlanService {

	@Autowired
	private ProductinfoMapper productinfoMapper;
	@Autowired
	private PlaninfoMapper planinfoMapper;
	@Autowired
	private OrderinfoMapper orderinfoMapper;

	/**
	 * 查询所有产品信息
	 * @return
	 */
	public List<Productinfo> searchAllProduct(){
		return productinfoMapper.selectByExample(null);
	}
	/**
	 * 根据订单编号查询产品数量
	 * @param pid
	 * @return
	 */
	public List<Orderinfo> searchProordnumByOrdid(int ordid){
		OrderinfoExample example = new OrderinfoExample();
		com.platformmake.model.entity.OrderinfoExample.Criteria cc = example.createCriteria();
		cc.andOrdidEqualTo(ordid);
		return orderinfoMapper.selectByExample(example);
	}
	/**
	 * 根据订单编号查询产品编号
	 * @param pid
	 * @return
	 */
	public List<Orderinfo> searchProidByOrdid(int ordid){
		OrderinfoExample example = new OrderinfoExample();
		com.platformmake.model.entity.OrderinfoExample.Criteria cc = example.createCriteria();
		cc.andOrdidEqualTo(ordid);
		return orderinfoMapper.selectByExample(example);

	}
	/**
	 * 查询所有订单
	 * @return
	 */
	public List<Orderinfo> searchAllOrder(){
		return orderinfoMapper.selectByExample(null);
	}
	/**
	 * 根据订单编号查找计划
	 * @param ordid
	 * @return
	 */
	public List<Planinfo> searchPlanByOrdid(int ordid){
		PlaninfoExample example = new PlaninfoExample();
		Criteria cc = example.createCriteria();
		cc.andOrdidEqualTo(ordid);
		return planinfoMapper.selectByExample(example);
	}
	/**
	 * 根据计划编号查询该计划的产品编号
	 * @param pid
	 * @return
	 */
	public List<Planinfo> searchProidByPlanid(int planid){
		PlaninfoExample example = new PlaninfoExample();
		Criteria cc = example.createCriteria();
		cc.andPlanidEqualTo(planid);
		return planinfoMapper.selectByExample(example);

	}
	/**
	 * 根据计划编号查询该计划的订单编号
	 * @param pid
	 * @return
	 */
	public List<Planinfo> searchOrdidByPlanid(int planid){
		PlaninfoExample example = new PlaninfoExample();
		Criteria cc = example.createCriteria();
		cc.andPlanidEqualTo(planid);
		return planinfoMapper.selectByExample(example);

	}
	/**
	 * 查询所有计划
	 * @return
	 */
	public List<Planinfo> searchAllPlan(){
		return planinfoMapper.selectByExample(null);
	}
	/**
	 * 启动计划
	 * @param plan
	 * @return
	 */
	public boolean addStartPlan(int planid) {
		PlaninfoExample example = new PlaninfoExample();
		Criteria cc = example.createCriteria();
		cc.andPlanidEqualTo(planid);
		List<Planinfo> list = planinfoMapper.selectByExample(example);
		Planinfo plan = list.get(0);
		//修改计划状态为已启动
		plan.setPlanstate(20);
		planinfoMapper.updateByPrimaryKey(plan);
	//	((Planinfo) plan).setPlanstate(20);
		//创建新工单
//		Workinfo work = new Workinfo(0,null,null,0,10,plan.getPlanid(),0,plan.getProid(),null,null);
	//	int i = workinfoMapper.insert(work);
	//	return i>0;
		return true;
	}
	/**
	 * 修改未启动的计划
	 * @param plan
	 * @return
	 */
	public boolean modPlan(Planinfo plan){
		//if(plan.getPlanstate()==10) {
		int i=planinfoMapper.updateByPrimaryKey(plan);
			return true;
	//	}
		//   return false;
	}
	/**
	 * 删除未启动的计划；若生产中订单只有一个生产计划，则不能删除
	 * @param plan
	 * @return
	 */
	public boolean delPlan(int planid) {
		//获取计划id对应的该计划
		  Planinfo plan = planinfoMapper.selectByPrimaryKey(planid);
		//未启动的计划，可删除
		  if(plan.getPlanstate()==10) {
		    //获取订单编号对应的订单
			Orderinfo order = orderinfoMapper.selectByPrimaryKey(plan.getOrdid());
			//订单不在生产中,可删除
			if(order.getOrdstate()!=40) {
				int i = planinfoMapper.deleteByPrimaryKey(planid);
				return i>0;
				}else {
						//生产中订单有几个生产计划
						PlaninfoExample example = new PlaninfoExample();
						Criteria cc = example.createCriteria();
						cc.andOrdidEqualTo(order.getOrdid());
						List<Planinfo> list = planinfoMapper.selectByExample(example);
						//大于1个
						if(list.size()>1) {
						int i = planinfoMapper.deleteByPrimaryKey(planid);
						return i>0;
						}
	           }
		  }
		  return false;
	}
	
	/**
	 * 生产计划的查询
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Planinfo> searchPlan(Planinfo cond, int pageNum, int pageSize){
		PlaninfoExample example = new PlaninfoExample();
		Criteria cc = example.createCriteria();

		//1.计划编号查询
		if(null != cond.getPlanid() && cond.getPlanid() > 0) {
			cc.andPlanidEqualTo(cond.getPlanid());	
		}
		//2.订单编号查询
		if(null != cond.getOrdid() && cond.getOrdid() > 0) {
			cc.andOrdidEqualTo(cond.getOrdid());
		}
		//3.产品id查询
		if(null != cond.getProid() && cond.getProid() > 0) {
			cc.andProidEqualTo(cond.getProid());
		}
		//4.计划状态查询
		if(null != cond.getPlanstate() && cond.getPlanstate() > 0) {
			cc.andPlanstateEqualTo(cond.getPlanstate());
		}
		
		//在开始查询之前，启动分页插件
		PageHelper.startPage(pageNum, pageSize);
		//开始查询
		List<Planinfo> list = planinfoMapper.selectByExample(example);
				
		//返回值
		return new PageInfo<Planinfo>(list);
	}
	/**
	 * 新增生产计划
	 * @param plan
	 * @return
	 */
	public boolean addPlan(Planinfo plan) {
		Orderinfo order = orderinfoMapper.selectByPrimaryKey(plan.getOrdid());
		//订单已接单
		if(order.getOrdstate()==20 ||order.getOrdstate()==40) {
			//默认计划未启动
			plan.setPlanstate(10);
			planinfoMapper.insert(plan);
			//新建计划后，将订单状态改为生产中
			order.setOrdstate(40);
			orderinfoMapper.updateByPrimaryKeySelective(order);
			
			return true;
		}
			return false;
	}
}
