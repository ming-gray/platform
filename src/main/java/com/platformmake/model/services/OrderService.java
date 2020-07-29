/**
 * 订单管理模块
 */
package com.platformmake.model.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.ConnectMapper;
import com.platformmake.model.dao.OrderinfoMapper;
import com.platformmake.model.dao.PlaninfoMapper;
import com.platformmake.model.dao.ProductinfoMapper;
import com.platformmake.model.dao.WorkinfoMapper;
import com.platformmake.model.entity.Connect;
import com.platformmake.model.entity.ConnectExample;
import com.platformmake.model.entity.ConnectExample.Criteria;
import com.platformmake.model.entity.Orderinfo;
import com.platformmake.model.entity.OrderinfoExample;
import com.platformmake.model.entity.Planinfo;
import com.platformmake.model.entity.PlaninfoExample;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.entity.ProductinfoExample;
import com.platformmake.model.entity.Workinfo;
import com.platformmake.model.entity.WorkinfoExample;

@Service
public class OrderService {
	@Autowired
	private OrderinfoMapper orderMapper;
	@Autowired
	private	ProductinfoMapper pro;
	@Autowired
	private	ConnectMapper con;
	@Autowired
	private	PlaninfoMapper pla;
	@Autowired
	private WorkinfoMapper work;
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date times;
	/**
	 * 查下拉框
	 * @return
	 */
	public List<Productinfo> searchAllPro() {
		return pro.selectByExample(null);
	}
	/**
	 * 新建订单
	 * 
	 */
	public boolean addOrder(Orderinfo ord) {
		//判断产品名是否合法
		ProductinfoExample example = new ProductinfoExample();
		com.platformmake.model.entity.ProductinfoExample.Criteria cc = example.createCriteria();
		//如果不存在相同名称的产品则不合法，无法新建订单
		cc.andProidEqualTo(ord.getProid());
		
		List<Productinfo> list=pro.selectByExample(example);
		if(list.size()>0) {
			//产品名合法
			orderMapper.insert(ord);
			ord.setOrdstate(10);
			return true;
		}else {
			//产品名不合法
			return false;
		}
	}
	/**
	 * 接受订单
	 * 
	 */
	public boolean acceptOrder(Orderinfo ord){
		//计算工厂产能
		//计算时间差
		SimpleDateFormat days = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_1= days.format(ord.getOrddl());
		String time_2 = days.format(new Date());
		Date begin = null;
		try {
			begin = days.parse(time_1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date end = null;
		try {
			end = days.parse(time_2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long  timebreak=(end.getTime()-begin.getTime())/1000;//除以1000是为了转换成秒 
		//long days = (ord.getOrddl().getTime() - new Date().getTime() + 1000000)/(60*60*24*1000);
		//在设备产品表中查询能生产该产品的全部设备的产能
		ConnectExample example = new ConnectExample();
		Criteria cc = example.createCriteria();
		cc.andProidEqualTo(ord.getProid());
		
		List<Connect> list = con.selectByExample(example);
		if(ord.getApc()!=0) {
			if(ord.getApc()>=ord.getProordnum())
			{	
				ord.setOrdstate(20);
				orderMapper.updateByPrimaryKey(ord);
				return true;
			}
		}
		int totalapc=0;
        for(Connect con :list){
        	totalapc = (int) (totalapc+timebreak*con.getYield());
        }
        if ( totalapc >= ord.getProordnum()){
            // 可接单 状态更新为已接单状态
        	ord.setOrdstate(20);
        	ord.setApc(totalapc); 
        	//转成生产计划
            return true;
		}
		return false;
	}
	/**
	 * 拒绝订单
	 * @param ord
	 * @return
	 */
	public boolean refuseOrder(Orderinfo ord){
		if(ord.getOrdstate()==10)
		{
			ord.setOrdstate(30);
			orderMapper.updateByPrimaryKey(ord);
		}
		return true;
	}
	/**
	 * 修改订单状态
	 * @param ord
	 * @return
	 */
	public boolean updateOrderState(Orderinfo ord){
		if(ord.getOrdstate() ==50||ord.getOrdstate() ==30)
			return false;
		if(ord.getOrdstate() ==10) {
			return acceptOrder(ord);
		}
		if(ord.getOrdstate() ==40)
			return finishOrder(ord);
		return false;
	}
	/**
	 * 完成订单
	 * @param ord
	 * @return
	 */
	public boolean finishOrder(Orderinfo ord){
		//查询该订单的状态是否为生产中
		if(ord.getOrdstate()==40) {
			//查询该订单的生产计划状态是否为已完成
			PlaninfoExample example = new PlaninfoExample();
			com.platformmake.model.entity.PlaninfoExample.Criteria cc = example.createCriteria();
			cc.andOrdidEqualTo(ord.getOrdid());
			
			List<Planinfo> plans= pla.selectByExample(example);
			for(Planinfo pla:plans) {
				if(pla.getPlanstate()!=40)
					return false;
			}
			ord.setOrdstate(50);
			orderMapper.updateByPrimaryKey(ord);
		}
		return true;
	}
	/**
	 * 删除订单
	 * @param ordid
	 * @return
	 */
	public boolean deleteByOrderid(int ordid){
		//处于已接单和生产中的订单不能删除
		Orderinfo ord=orderMapper.selectByPrimaryKey(ordid);
		if(ord.getOrdstate()==20 || ord.getOrdstate() == 40)
			return false;
			int i=orderMapper.deleteByPrimaryKey(ordid);
			return i>0;
	}
	/**
	 * 修改订单
	 * @param ord
	 * @return
	 */
	public boolean modOrderbyid(Orderinfo ord) {
		ProductinfoExample example = new ProductinfoExample();
		com.platformmake.model.entity.ProductinfoExample.Criteria cc = example.createCriteria();
		cc.andProidEqualTo(ord.getProid());
		
		List<Productinfo> list = pro.selectByExample(example);
		//产品名合法
		if(list.size()>0) {
			//数量正确
			if(ord.getProordnum()>=0&&ord.getQuacom()>=0&&ord.getApc()>=0)
				//截止时间合理
			//	if(new Date().before(ord.getOrddl()))
				//{
					orderMapper.updateByPrimaryKey(ord);
					return true;
			//	}
		}
		return false;
	}
	/**
	 * 分页查询
	 * @param ord
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Orderinfo> searchOrder(Orderinfo ord,int pageNum, int pageSize){
		OrderinfoExample example = new OrderinfoExample();
		com.platformmake.model.entity.OrderinfoExample.Criteria cc= example.createCriteria();
		if(null != ord.getOrdid()) {
			//添加订单号查询条件
			cc.andOrdidEqualTo(ord.getOrdid());
		}
		if(null != ord.getProid()) {
			//添加产品名查询条件
			cc.andProidEqualTo(ord.getProid());
		}
		if(null!= ord.getOrdstate()) {
			//添加订单状态查询条件
			cc.andOrdstateEqualTo(ord.getOrdstate());
		}
		//启动分页插件
		PageHelper.startPage(pageNum,pageSize);
		//实施查询
		List<Orderinfo> list =orderMapper.selectByExample(example);
		//返回值
		return new PageInfo<Orderinfo>(list);
}
}