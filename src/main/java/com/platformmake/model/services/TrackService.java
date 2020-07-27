package com.platformmake.model.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.DayWorkMapper;
import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.dao.OrderinfoMapper;
import com.platformmake.model.dao.ProductinfoMapper;
import com.platformmake.model.dao.TrackinfoMapper;
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.DayWork;
import com.platformmake.model.entity.DayWorkExample;
import com.platformmake.model.entity.Planinfo;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.entity.Trackinfo;
import com.platformmake.model.entity.TrackinfoExample;
import com.platformmake.model.entity.TrackinfoExample.Criteria;
import com.platformmake.model.entity.Workinfo;

@Service
public class TrackService {
	
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
	
	@Autowired
	private DayworkService dayworkservice;
	
	
	/**
	 * 主键查询跟踪(测试)
	 * @param id
	 * @return
	 */
	public Trackinfo searchByPrimaryKey(int id){
		return trackmapper.selectByPrimaryKey(id);
	}
	/**
	 * 查询跟踪(测试)
	 * @param tra
	 * @return
	 */
	public List<Trackinfo> searchAllByExample(Trackinfo tra){
		
		TrackinfoExample example = new TrackinfoExample();
		Criteria cc=example.createCriteria();
		cc.andProidEqualTo(1);
		return trackmapper.selectByExample(example);
	}
	
	/**
	 * 通过工单ID查跟踪
	 */
	
	public Trackinfo searchByWorkid(int workid){
		
		TrackinfoExample example = new TrackinfoExample();
		Criteria cc=example.createCriteria();
		cc.andWorkidEqualTo(workid);
		List<Trackinfo> list= trackmapper.selectByExample(example);
		Trackinfo tra=list.get(0);
		return tra;
	}
	/**
	 * 分页查询跟踪
	 */
	public PageInfo<Trackinfo> PageSearchtrackInfo(Trackinfo cond,int pageNum,int pageSize){
		TrackinfoExample example=new TrackinfoExample();
		Criteria cc=example.createCriteria();
		if( null!=cond.getId() ) {
			cc.andIdEqualTo(cond.getId());
		}
		if( null!=cond.getWorkid() ) {
			cc.andWorkidEqualTo(cond.getWorkid());
		}
		if( null!=cond.getProid()) {
			cc.andProidEqualTo(cond.getProid());
		}
		if( null!=cond.getPlanid() ) {
			cc.andPlanidEqualTo(cond.getPlanid());
		}
		if( null!=cond.getTrackstate() ) {
			cc.andTrackstateEqualTo(cond.getTrackstate());
		}
		//启动分页插件
		PageHelper.startPage(pageNum,pageSize);
		//不要添加任何代码
		List<Trackinfo>list=trackmapper.selectByExample(example);
		//返回值
		return new PageInfo<Trackinfo>(list);
	}
	
	/**
	 * 添加跟踪
	 * @param tra
	 * @return
	 */
	
	public boolean addTrack(int workid) {
		Workinfo work=workmapper.selectByPrimaryKey(workid);
		if(work!=null) {
			Trackinfo tra = new Trackinfo(0, workid, work.getPlanid(), work.getProid(),10, work.getWorkcount(), 0, null, null);
			work.setWorkstate(20);
			workmapper.updateByPrimaryKey( work );

			int i = trackmapper.insert(tra);
			
			if(i!=0) {
				return true;
			}
			return false;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 完成跟踪
	 */
	public boolean finishTrack(int id) {
		
		Trackinfo tra=trackmapper.selectByPrimaryKey(id);
		
		tra.setTrackstate(20);
		
		int planid=tra.getPlanid();
		
		int workid=tra.getWorkid();
		
		Workinfo work=workmapper.selectByPrimaryKey(workid);
		
		work.setWorkstate(30);
		
		workmapper.updateByPrimaryKey(work);
		trackmapper.updateByPrimaryKey(tra);
		
		
//		将完成的功能删除（看情况）
//		trackmapper.deleteByPrimaryKey(id);
//		dayworkservice.delectDaiwork(workid);
		return true;
		
	}
	
	/**
	 * 修改跟踪
	 */
	public boolean updataTrack(Trackinfo tra) {
		
		int i=trackmapper.updateByPrimaryKey(tra);
		
		if(i!=0) {
			return true;
		}
		else {
			return false;
		}
		
	}
	/**
	 * 删除跟踪
	 */
	public boolean delectTrack(int id) {
		Trackinfo tra=trackmapper.selectByPrimaryKey(id);
		int workid=tra.getWorkid();
		
		Workinfo work=workmapper.selectByPrimaryKey(workid);
		
		work.setWorkstate(10);
		
		workmapper.updateByPrimaryKey(work);
		
		dayworkservice.delectDaiwork(workid);
		trackmapper.deleteByPrimaryKey(id);
		return true;
		
	}


	
}
