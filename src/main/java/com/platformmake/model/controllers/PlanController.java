package com.platformmake.model.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.Orderinfo;
import com.platformmake.model.entity.Planinfo;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.services.PlanService;

/**
 * 生产计划控制类
 * @author Administrator
 *
 */
@RestController
public class PlanController {

	@Autowired
	private PlanService service;
	
	/**
	 * 初始化页面时，获取所有产品信息
	 * @return
	 */
	@RequestMapping("/initproduct")
	public List<Productinfo> doInitProducts(){
		
		return service.searchAllProduct();
	}
	/**
	 * 根据订单编号获取产品数量
	 * @param ordid
	 * @return
	 */
	@RequestMapping("/getproordnumbyordid")
	public List<Orderinfo> doGetProordnumByOrdid(int ordid){
		return service.searchProordnumByOrdid(ordid);
	}
	/**
	 * 根据订单编号获取产品编号
	 * @param ordid
	 * @return
	 */
	@RequestMapping("/getproidbyordid")
	public List<Orderinfo> doGetProidByOrdid(int ordid){
		return service.searchProidByOrdid(ordid);
	}
	/**
	 * 初始化页面时，获取所有订单信息
	 * @return
	 */
	@RequestMapping("/initorder")
	public List<Orderinfo> doInitOrders(){
		
		return service.searchAllOrder();
	}
	@RequestMapping("/getplanbyordid")
	public List<Planinfo> doGetPlanByOrdid(int ordid){
		return service.searchPlanByOrdid(ordid);
	}
	/**
	 * 当用户选择了计划，通过计划id获取产品id的方法
	 * @param pid
	 * @return
	 */
	@RequestMapping("/getproid")
	public List<Planinfo> doGetProid(int planid){
		return service.searchOrdidByPlanid(planid);
		
	}
	/**
	 * 当用户选择了计划，通过计划id获取订单id的方法
	 * @param pid
	 * @return
	 */
	@RequestMapping("/getordid")
	public List<Planinfo> doGetOrdid(int planid){
		return service.searchOrdidByPlanid(planid);
		
	}
	/**
	 * 初始化页面时，获取所有计划信息
	 * @return
	 */
	@RequestMapping("/initplan")
	public List<Planinfo> doInitPlans(){
		
		return service.searchAllPlan();
	}
	@RequestMapping("/startplan")
	public boolean doaddStartPlan(int planid) {
		return service.addStartPlan(planid);
	//	return true;
	}
	/**
	 * 修改计划控制
	 * @param plan
	 * @return
	 */
	@RequestMapping("/modplan")
	public boolean doModPlan(Planinfo plan) {
		return service.modPlan(plan);
	//	return true;
	}
	/**
	 * 删除计划控制
	 * @param plan
	 * @return
	 */
	@RequestMapping("/delplan")
	public boolean doDelPlan(int planid) {
		return service.delPlan(planid);
	}
	
	/**
	 * 生产计划查询控制
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/searchplan")
	public PageInfo<Planinfo> doSearchPlan(Planinfo cond, int pageNum, int pageSize){
		return service.searchPlan(cond, pageNum, pageSize);
	}
	/**
	 * 新增生产计划控制
	 * @param plan
	 * @return
	 */
	@RequestMapping("/addplan")
	public boolean doAddPlan(Planinfo plan) {
		return service.addPlan(plan);
	}
	
}
