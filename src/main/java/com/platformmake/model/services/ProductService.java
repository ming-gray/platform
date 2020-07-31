package com.platformmake.model.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.OrderinfoMapper;
import com.platformmake.model.dao.ProductinfoMapper;
import com.platformmake.model.entity.Orderinfo;
import com.platformmake.model.entity.OrderinfoExample;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.entity.ProductinfoExample;
import com.platformmake.model.entity.ProductinfoExample.Criteria;



/**
 * 产品管理业务 逻辑模块
 * @author Administrator
 *
 */
@Service
public class ProductService {

	@Autowired
	private ProductinfoMapper productinfoMapper;
	@Autowired
	private OrderinfoMapper orderinfoMapper;
	
	/**
	 * 修改产品信息，要求 不可重名
	 * @param product
	 * @return true 修改成功
	 */
	 public boolean modProduct(Productinfo product){
		 boolean list = checkProname(product.getProname());
		 //不重名时
		 if(list) {
			 productinfoMapper.updateByPrimaryKeySelective(product);
			 return true;
		 }else {
			 return false;
		 }
	 }
	/**
	 * 根据产品id删除产品，且存在关联已接单订单时不可删除
	 * @param proid
	 * @return true 删除成功
	 */
	 public boolean delProduct(int proid){
		 OrderinfoExample example = new OrderinfoExample();
		 com.platformmake.model.entity.OrderinfoExample.Criteria cc = example.createCriteria();
		 //查询订单中的产品id
		 cc.andProidEqualTo(proid);
		List<Orderinfo> list = orderinfoMapper.selectByExample(example);
		//订单表中有此产品关联的已接单的订单
		if(list.size()>0 && list.get(0).getOrdstate()==20) {
			return false;
		}else {
			int num = productinfoMapper.deleteByPrimaryKey(proid);
			 return num>0;
		}
	 }
	/**
	 * 分页查询
	 * @param cond
	 * @param pageNum
	 * @param pageSize
	 * @return 返回查询结果
	 */
	public PageInfo<Productinfo> searchProduct(Productinfo cond, int pageNum, int pageSize){
		ProductinfoExample example = new ProductinfoExample();
		Criteria cc = example.createCriteria();
		//判断字符串是否为空和空字符串
		if(null != cond.getProname() && !"".equals(cond.getProname())) {
			//根据产品名称进行模糊查询
			cc.andPronameLike("%"+cond.getProname()+"%");
		}
		//在开始查询之前，启动分页插件
		PageHelper.startPage(pageNum, pageSize);
		//开始查询
		List<Productinfo> list = productinfoMapper.selectByExample(example);
				
		//返回值
		return new PageInfo<Productinfo>(list);
		
	}
	/**
	 * 添加产品
	 * @param product
	 * @return true 添加成功
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	public boolean addProduct(Productinfo product){
		boolean list = checkProname(product.getProname());
		
		       //不重名时
		      if(list) {
			      productinfoMapper.insert(product);
			      return true;
		}else {
			return false;
		}
		
	}
	/**
	 * 实时检查产品名称是否冲突
	 * @param proname
	 * @return true 不冲突
	 */
	public boolean checkProname(String proname) {
		//1. 检查产品名称是否冲突
		ProductinfoExample example = new ProductinfoExample();
		Criteria cc = example.createCriteria();
		//添加不可重名条件
		cc.andPronameEqualTo(proname);
		List<Productinfo> list = productinfoMapper.selectByExample(example);
		
		return list.size() == 0;
	}
}
