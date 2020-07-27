package com.platformmake.model.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.ConnectMapper;
import com.platformmake.model.dao.DayWorkMapper;
import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.dao.TrackinfoMapper;
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.DayWork;
import com.platformmake.model.entity.DayWorkExample;
import com.platformmake.model.entity.DayWorkExample.Criteria;
import com.platformmake.model.entity.Equipinfo;
import com.platformmake.model.entity.Trackinfo;
import com.platformmake.model.entity.Workinfo;


@Service
public class DayworkService {

	@Autowired
	private DayWorkMapper dayworkmapper;
	
	
	@Autowired
	private TrackinfoMapper trackmapper;
	
	@Autowired
	private WorkinfoMapper workmapper;
	
	@Autowired
	private ConnectMapper eqpromapper;
	
	@Autowired
	private TrackService trackservice;
	
	@Autowired
	private equip_pro eqpro;
	
	/**
	 * 清空报工
	 */
	public boolean clearTrack() {
		
		return true;
	}
	/**
	 * 新添报工
	 * 联调后取消注释
	 */
	public boolean addDaiwork(DayWork daywork) {
		int workid=daywork.getWorkid();
		
		Trackinfo track=trackservice.searchByWorkid(workid);
		Workinfo work=workmapper.selectByPrimaryKey(workid);
		
		System.out.println(track);
		System.out.println(work);
//		int eqid=work.getEqid();
//		int proid=work.getProid();
//		int count =daywork.getWorkingCount();
//		if(eqpro.checkyield(eqid, proid, count)) {
//			return false;
//		}
		
		daywork.setEqid(1);
//		daywork.setEqid(eqid);
		
		daywork.setUnquaCount(daywork.getWorkingCount()-daywork.getQuaCount() );
		daywork.setMark("新添");
		dayworkmapper.insert(daywork);
		
		if(track!=null) {
			int tempcount=track.getQualifiedCount();
			
			track.setQualifiedCount(tempcount+daywork.getQuaCount() );
			
			trackmapper.updateByPrimaryKey(track);
			System.out.println(track);
			return true;
		}
		
		return false;
	}
	/**
	 * 删除报工
	 */
	public boolean delectDaiwork (int workid) {
		DayWorkExample example=new DayWorkExample();
		Criteria cc=example.createCriteria();
		cc.andWorkidEqualTo(workid);
		dayworkmapper.deleteByExample(example);
		
		return true;
		
	}
	
	/**
	 * 修改报工
	 */
	
	public boolean updataDaiwork(DayWork Work) {
		
		return true;
	}
	
	/**
	 * 查询报工
	 */
	public PageInfo<DayWork> searchDaiwork (DayWork cond, int pageNum, int pageSize){
		DayWorkExample example=new DayWorkExample();
		Criteria cc=example.createCriteria();
		if( null!=cond.getId() ) {
			cc.andIdEqualTo(cond.getId());
		}
		if(null!=cond.getEqid()) {
			cc.andEqidEqualTo(cond.getEqid());
		}
		if(null!=cond.getWorkid()) {
			cc.andWorkidEqualTo(cond.getWorkid());
		}
		
		
		//启动分页插件
		PageHelper.startPage(pageNum,pageSize);
		//不要添加任何代码
		List<DayWork>list=dayworkmapper.selectByExample(example);
		//返回值
		return new PageInfo<DayWork>(list);
		
	}
	
	
}
