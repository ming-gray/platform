package com.platformmake.model.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.Planinfo;
import com.platformmake.model.entity.Workinfo;
import com.platformmake.model.services.WorkinfoService;

/**
  *  生产调度
 * @author ming
 *
 */
@RestController
public class WorkinfoController {

	@Autowired
	private WorkinfoService service;
	
	/**
	  *  添加生产调度信息
	 * @param wokinfo
	 * @return
	 */
	@RequestMapping("/addwork")
	public boolean doAddWorkinfo(Workinfo workinfo) {
		return service.addWorkinfo(workinfo);
	}
	
	/**
	  *  启动工单	
	 * @param workid
	 * @return
	 */
	@RequestMapping("/start")
	public Workinfo	doStartWorkinfo(int workid) {
		return service.startWorkinfo(workid);
	}
	
	/**
	  *  根据工单ID查询工单	
	 * @param workid
	 * @return
	 */
	@RequestMapping("/searchw")
	public Workinfo doSelectByWorkid(int workid) {
		return service.selectByWorkid(workid);
	}
	
	/**
	  *  分页查询工单	
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/searchall")
	public PageInfo<Workinfo> doListWorkinfo(Workinfo cond, int pageNum, int pageSize){
		return service.listWorkinfo(cond, pageNum, pageSize);
	}
	
	/**
	  *  根据计划ID查询工单	
	 * @param planid
	 * @return
	 */
	@RequestMapping("/searchp")
	public List<Workinfo> doSelectByPlanid(int planid){
		return service.selectByPlanid(planid);
	}
	
	/**
	  *  删除未启动工单	
	 * @param workid
	 * @return
	 */
	@RequestMapping("/delwork")
	public boolean doDeleteById(int workid) {
		return service.deleteById(workid);
	}
	
	/**
	  *  更新工单	
	 * @param wokinfo
	 * @return
	 */
	@RequestMapping("/modwork")
	public boolean doUpdateWorkinfo(Workinfo workinfo) {
		return service.updateWorkinfo(workinfo);
	}
	
	/**
	   *  根据计划id获得产品id
	 * @param planid
	 * @return
	 */
	@RequestMapping("/getprid")
	public int doSearchProByPlan(int planid) {
		return service.searchProByPlan(planid);
	}
	
	/**
	  * 指定生产设备	
	 * @param proid
	 * @return
	 */
	@RequestMapping("/seteq")
	public Workinfo	doSetEquiWorkinfo(Workinfo workinfo) {
		return service.setEquiWorkinfo(workinfo);
	}
}
