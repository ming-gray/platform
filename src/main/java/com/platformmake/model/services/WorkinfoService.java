package com.platformmake.model.services;

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
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.Connect;
import com.platformmake.model.entity.ConnectExample;
import com.platformmake.model.entity.Equipinfo;
import com.platformmake.model.entity.Planinfo;
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
	
	/**
	  *  添加生产调度信息
	 * @param wokinfo
	 * @return
	 */
	public boolean addWorkinfo (Workinfo workinfo) {
		// 获取生产计划
		Planinfo planinfo = pm.selectByPrimaryKey(workinfo.getPlanid());
		
		// 对已启动的生产计划新建工单
		if(planinfo.getPlanstate() == 20) {
			wm.insert(workinfo);
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
			System.out.println("该工单不存在，请重试");
			return null;
		}
	    if (workinfo.getWorkstate() == 10) {
			// 启动工单
			workinfo.setWorkstate(20);
			wm.updateByPrimaryKeySelective(workinfo);
			
			// 启动设备
			Equipinfo eqinfo = em.selectByPrimaryKey(workinfo.getEqid());
			eqinfo.setEqstate(20);
			em.updateByPrimaryKeySelective(eqinfo);
			
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
	  *   删除未启动工单
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
		WorkinfoExample example = new WorkinfoExample();
		Criteria cc = example.createCriteria();
		cc.andPlanidEqualTo(workinfo.getPlanid());
		//获取计划状态
		
		
		List<Workinfo> list = wm.selectByExample(example);
		if(list.size() < 2) {
			System.out.println("工单记录不足，删除失败");
			return false;
		}
		else if(workinfo.getWorkstate() == 10) {
			wm.deleteByPrimaryKey(workid);
			return true;
		}
		else {
			System.out.println("已启动工单不可删除");
			return false;
		}
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
//	public Workinfo	setEquiWorkinfo(int proid) {
//		ConnectExample example = new ConnectExample();
//		com.platformmake.model.entity.ConnectExample.Criteria cc = example.createCriteria();
//		cc.andProidEqualTo(proid);
//		
//		List<Connect> list = cm.selectByExample(example);
//		//获取与产品对应的设备
//		if(list)
//	}
	
	
}
