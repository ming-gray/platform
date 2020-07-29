package com.platformmake.model.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.SessionScope;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.Orderinfo;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.services.OrderService;

@RestController
public class OrderServiceController<searchOrder> {

	//当前用户信息在Session中Key名字
	public static final String CURRENT_USER = "CURRENTUSER";
	@Autowired
	private OrderService service;
	/**
	 * 新建订单
	 */
	@RequestMapping("/addorder")
	public boolean doAddOrder(Orderinfo ord) {
		return service.addOrder(ord);
	}
	@RequestMapping("/accorder")
	public boolean doAcceptOrder(Orderinfo ord) {
		return service.acceptOrder(ord);
	}
	@RequestMapping("/reforder")
	public boolean doRefuseOrder(Orderinfo ord) {
		return service.refuseOrder(ord);
	}
	@RequestMapping("/updatestate")
	public boolean doUpdateOrderState(Orderinfo ord) {
		return service.updateOrderState(ord);
	}
	
	@RequestMapping("/finorder")
	public boolean doFinishOrder(Orderinfo ord) {
		return service.finishOrder(ord);
	}
	@RequestMapping("/delorder")
	public boolean doDeleteByOrderid(int ordid) {
		return service.deleteByOrderid(ordid);
	}
	@RequestMapping("/modorder")
	public boolean doModOrderbyid(Orderinfo ord) {
		return service.modOrderbyid(ord);
	}
	@RequestMapping("/seaorder")
	public PageInfo<Orderinfo> searchOrder(Orderinfo ord,int pageNum, int pageSize) {
		return	service.searchOrder(ord, pageNum, pageSize);
	}
	@RequestMapping("/initpro")
	public List<Productinfo> doInitProvinces(){
		return service.searchAllPro();
	}
}
