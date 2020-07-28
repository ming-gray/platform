package com.platformmake.model.services;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.WorkinfoExample.Criteria;
import com.platformmake.model.dao.ConnectMapper;
import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.dao.PlaninfoMapper;
import com.platformmake.model.dao.ProductinfoMapper;
import com.platformmake.model.dao.TrackinfoMapper;
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.Connect;
import com.platformmake.model.entity.ConnectExample;
import com.platformmake.model.entity.Equipinfo;
import com.platformmake.model.entity.Planinfo;
import com.platformmake.model.entity.PlaninfoExample;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.entity.ProductinfoExample;
import com.platformmake.model.entity.Trackinfo;
import com.platformmake.model.entity.Workinfo;
import com.platformmake.model.entity.WorkinfoExample;

/**
  *  生产调度
 * @author ming
 *
 */
@Service
public class WorkinfoService {

	@Autowired
	private WorkinfoMapper wm;
	
	@Autowired
	private PlaninfoMapper pm;
	
	@Autowired
	private ConnectMapper cm;
	
	@Autowired
	private EquipinfoMapper em;
	
	@Autowired
	private ProductinfoMapper prom;
	
	@Autowired
	private TrackinfoMapper tm;
	
	/**
	  *  添加生产调度信息，新建工单
	 * @param wokinfo
	 * @return
	 */
	public boolean addWorkinfo (Workinfo workinfo) {
		// 获取生产计划
		Planinfo planinfo = pm.selectByPrimaryKey(workinfo.getPlanid());
		
		// 对已启动的生产计划新建工单
		if(planinfo.getPlanstate() == 20) {					
			
			// 设置创建时间
			workinfo.setCretime(new Timestamp(new Date().getTime()));
			
			// 默认第一次修改时间为创建时间
			workinfo.setUpdtime(new Timestamp(new Date().getTime()));
			wm.updateByPrimaryKeySelective(workinfo);
			wm.insert(workinfo);
			System.out.println(workinfo);
			
			return true;
		}
		return false;
	}
	
	/**
	  *  启动工单
	 * @param workid
	 * @return
	 */
	public Workinfo	startWorkinfo(int workid) {
		Workinfo workinfo = wm.selectByPrimaryKey(workid);
		if(workinfo == null) {
			System.out.println("该工单不存在");
			return null;
		}
	    if (workinfo.getWorkstate() == 10) {
			// 启动工单
			workinfo.setWorkstate(20);
			wm.updateByPrimaryKeySelective(workinfo);
			
			// 启动设备,设备状态10为启动，可能需要改
			Equipinfo eqinfo = em.selectByPrimaryKey(workinfo.getEqid());
			eqinfo.setEqstate(10);
			em.updateByPrimaryKeySelective(eqinfo);
			
			// 设置开始日期和更新时间	
			workinfo.setWorksttime(new Timestamp(new Date().getTime()));
			workinfo.setUpdtime(new Timestamp(new Date().getTime()));
			wm.updateByPrimaryKey(workinfo);
			
			// 生成生产跟踪记录
			Trackinfo trackinfo = new Trackinfo();
			trackinfo.setCratetime(new Timestamp(new Date().getTime()));
			trackinfo.setUptime(new Timestamp(new Date().getTime()));
			trackinfo.setPlanid(workinfo.getPlanid());
			trackinfo.setProid(workinfo.getProid());
			trackinfo.setTrackstate(10);
			trackinfo.setWorkid(workinfo.getWorkid());
			trackinfo.setWorkingCount(workinfo.getWorkcount());
			tm.insertSelective(trackinfo);
			
			return workinfo;
		}else {
			System.out.println("该工单已在生产中或已完成");
			return null;
		}
	}
	
	/**
	  *  根据工单ID查询工单
	 * @param workid
	 * @return
	 */
	public Workinfo selectByWorkid(int workid){
		Workinfo workinfo = wm.selectByPrimaryKey(workid);
		return workinfo;
	}
	
	/**
	  *  分页查询工单
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Workinfo> listWorkinfo (Workinfo cond, int pageNum, int pageSize){
		WorkinfoExample example = new WorkinfoExample();
		Criteria cc = example.createCriteria();
		
		if(null != cond.getWorkid()) {
			cc.andWorkidEqualTo(cond.getWorkid());
		}
		if(null != cond.getPlanid()) {
			cc.andPlanidEqualTo(cond.getPlanid());
		}
		if(null != cond.getProid()) {
			cc.andProidEqualTo(cond.getProid());
		}
		if(null != cond.getEqid()) {
			cc.andEqidEqualTo(cond.getEqid());
		}
		if(null != cond.getCretime()) {
			cc.andCretimeEqualTo(cond.getCretime());
		}
		if(null != cond.getUpdtime()) {
			cc.andUpdtimeEqualTo(cond.getUpdtime());
		}
		if(null != cond.getWorkcount()) {
			cc.andWorkcountEqualTo(cond.getWorkcount());
		}
		if(null != cond.getWorkstate()) {
			cc.andWorkstateEqualTo(cond.getWorkstate());
		}
		if(null != cond.getWorksttime()) {
			cc.andWorksttimeEqualTo(cond.getWorksttime());
		}
		if(null != cond.getWorkentime()) {
			cc.andWorkentimeEqualTo(cond.getWorkentime());
		}
		
		
		// 启动分页插件
		PageHelper.startPage(pageNum, pageSize);
		List<Workinfo> list = wm.selectByExample(example);
		
		return new PageInfo<Workinfo>(list);
	}
	
	/**
	  *   删除未启动或已完成工单
	 * @param workid
	 * @return
	 */
	public boolean deleteById(int workid) {
		
		Workinfo workinfo = wm.selectByPrimaryKey(workid);
		if(workinfo ==null) {
			System.out.println("该工单不存在，删除失败");
			return false;
		}
		
		// 每一条已启动计划中必须有一条以上工单记录
		// 获取计划状态
		WorkinfoExample example = new WorkinfoExample();
		Criteria cc = example.createCriteria();
		cc.andPlanidEqualTo(workinfo.getPlanid());

		List<Workinfo> list = wm.selectByExample(example);
		if(pm.selectByPrimaryKey(workinfo.getPlanid()).getPlanstate() == 20) {
			if(list.size() < 2) {
				// 也可以是删除成功，但是把已启动的计划更新为未启动？
				System.out.println("工单记录不足，删除失败");
				return false;
			}
			else if(workinfo.getWorkstate() != 20) {
				wm.deleteByPrimaryKey(workid);
				return true;
			}
			else {
				System.out.println("已启动工单不可删除");
				return false;
			}
		}
		
		System.out.println("工单对应计划未启动");
		return false;
	}
	
	/**
	  *  更新工单
	 * @param wokinfo
	 * @return
	 */
	public boolean updateWorkinfo(Workinfo workinfo) {
		Workinfo w = wm.selectByPrimaryKey(workinfo.getWorkid());
		if(w.getWorkstate() == 20) {
			System.out.println("已启动工单不可编辑");
			return false;
		}
		else {
			wm.updateByPrimaryKeySelective(workinfo);
			
			// 设置更新时间			
			workinfo.setUpdtime(new Timestamp(new Date().getTime()));
			wm.updateByPrimaryKey(workinfo);
			
			return true;
		}
		
	}
	
	/**
	  *  根据计划ID查询工单
	 * @param planid
	 * @return
	 */
	public List<Workinfo> selectByPlanid(int planid){
		WorkinfoExample example = new WorkinfoExample();
		Criteria cc = example.createCriteria();
		cc.andPlanidEqualTo(planid);
		
		List<Workinfo> list = wm.selectByExample(example);
		return list;
	}
	
	/**
	  *  指定生产设备
	 * @param proid
	 * @return
	 */
	public Workinfo	setEquiWorkinfo(Workinfo workinfo) {
		
		ConnectExample example = new ConnectExample();
		com.platformmake.model.entity.ConnectExample.Criteria cc = example.createCriteria();
		cc.andProidEqualTo(workinfo.getProid());
		
		List<Connect> list = cm.selectByExample(example);
		//获取与产品对应的设备
		if(list.size() == 0) {
			System.out.println("找不到对应设备");
			return null;
		}
		for(Connect c: list) {
			Equipinfo eq = em.selectByPrimaryKey(c.getEqid());
			
			// 判断设备状态为未启用
			if(eq.getEqstate() == 20) {
				// 还要判断产能：日产能×间隔天数>工单数量
				System.out.println("我进来了");
				Planinfo planinfo = pm.selectByPrimaryKey(workinfo.getPlanid());
				long daysbtween = planinfo.getDdl().getTime() - new Date().getTime();
				if(daysbtween * c.getYield() > planinfo.getPlancount()) {
					workinfo.setEqid(eq.getEqid());
					wm.updateByPrimaryKeySelective(workinfo);
					System.out.println("设备更新了" + eq.getEqid());
					break;
				}
			}
			else {
				System.out.println("设备已启用或发生故障");
				return null;
			}
		}
		// 设置更新时间
		workinfo.setUpdtime(new Timestamp(new Date().getTime()));
		wm.updateByPrimaryKeySelective(workinfo);
		System.out.println(workinfo);
		return workinfo;
	}
	
	/**
	  *  获取所有生产计划
	 * 
	 * @return
	 */
	public List<Planinfo> searchAllPlans(){
		return pm.selectByExample(null);
		
	}
	
	/**
	  *  根据计划id获得产品id
	 * 
	 * @param planid
	 * @return
	 */
	public int searchProByPlan(int planid){
		Planinfo planinfo = pm.selectByPrimaryKey(planid);
		return planinfo.getProid();		
	}
}
