package com.platformmake.model.services;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.DayWorkMapper;
import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.dao.OrderinfoMapper;
import com.platformmake.model.dao.PlaninfoMapper;
import com.platformmake.model.dao.ProductinfoMapper;
import com.platformmake.model.dao.TrackinfoMapper;
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.DayWork;
import com.platformmake.model.entity.DayWorkExample;
import com.platformmake.model.entity.Orderinfo;
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
	private PlaninfoMapper pm;
	
	@Autowired
	private DayWorkMapper daymapper;
	
	@Autowired
	private DayworkService dayworkservice;
	
	
	/**
	 * 按ID查询工单（测试）
	 * @param id
	 * @return
	 */
	public Trackinfo searchByPrimaryKey(int id){
		return trackmapper.selectByPrimaryKey(id);
	}
	/**
	 * 按查询跟踪集，测试
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
	 * ͨ按ID工单查询
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
	 *分页查询
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

		PageHelper.startPage(pageNum,pageSize);

		List<Trackinfo>list=trackmapper.selectByExample(example);
		//����ֵ
		return new PageInfo<Trackinfo>(list);
	}
	
	/**
	 *添加报工
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
	 * 完成报工
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
		if(workmapper.hasRelatedUnfinishedSchedule(work.getPlanid()) == 0) {
			int finishedNum = workmapper.hasFinishedNum(work.getPlanid());
			Planinfo planinfo = pm.selectByPrimaryKey(work.getPlanid());
			if(planinfo.getPlancount() <= finishedNum) {
				planinfo.setPlanstate(30);
				planinfo.setPlanentime(new Date());
				pm.updateByPrimaryKeySelective(planinfo);
				Orderinfo orderinfo = ordmapper.selectByPrimaryKey(planinfo.getOrdid());
				orderinfo.setOrdstate(50);
				ordmapper.updateByPrimaryKeySelective(orderinfo);				
			}		
		}
		
		return true;
		
	}
	
	/**
	 * 更新报工
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
	 * 删除报工
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
