package com.platformmake.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.services.ProductService;

/**
 * 产品管理 控制类
 * @author Administrator
 *
 */
@RestController//该类下所有方法均转换成json形式
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	/**
	 * 处理修改产品请求
	 * @param product
	 * @return
	 */
	@RequestMapping("/modpro")
	public boolean doModPro(Productinfo product) {
		return service.modProduct(product);
	}
	/**
	 * 处理删除产品请求
	 * @param proid
	 * @return
	 */
	@RequestMapping("/delpro")
	public boolean doDelPro(int proid){
		return service.delProduct(proid);
	}
	/**
	 * 处理产品分页查询
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/searchpro")
	public PageInfo<Productinfo> doSearchPro(Productinfo cond, int pageNum, int pageSize){
		return service.searchProduct(cond, pageNum, pageSize);
	}
	/**
	 * 处理添加产品请求
	 * @param product
	 * @return
	 */
	@RequestMapping("/addpro")
	public boolean doAddPro(Productinfo product) {
		//直接调用添加方法
		return service.addProduct(product);
	}

}
