package com.platformmake.model.controllers;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.Connect;
import com.platformmake.model.entity.Equipinfo;
import com.platformmake.model.services.EquipinfoService;
import com.platformmake.model.services.equip_pro;

/**
 * 
 * 
 * @return
 */

@RestController
public class EquipinfoController {
	
	private static final String CURRENT_EQUIP = "CURRENTEQU";
	@Autowired
	private EquipinfoService equservice;
	
	@Autowired
	private equip_pro conservice;
	
	
	
	/**
	 * 添加设备
	 */
	@RequestMapping("/addequip")
	public boolean doAddEquip() {
		
		return equservice.addEquip();
		
	}
	
	/**
	 *	删除设备
	 * @param eqid
	 * @return
	 */
	@RequestMapping("/delequip")
	public boolean doDelEquip(int eqid) {
		
		return equservice.delEquip(eqid);
		
	}
	
	/**
	 * 修改设备
	 * @param equ
	 * @return
	 */
	@RequestMapping("/modequip")
	public boolean doModEquip(Equipinfo equ) {
		
		return equservice.modEquip(equ);
		
	}
	
	/**
	 * 分页查询设备表
	 * @param equ
	 * @param session
	 * @return
	 */
	@RequestMapping("/seaequip")
	public PageInfo<Equipinfo> searchEquip(Equipinfo cond,int equippageNum,int equippageSize){
		
		return equservice.searchEquip(cond, equippageNum, equippageSize);
		
	}
	
	/**
	 * 添加连接
	 */
	@RequestMapping("/addcon")
	public boolean doAddcon(int proid,int eqid, int yield) {
		
		return conservice.addconntct(proid, eqid, yield);
		
		
	}
	
	/**
	 *	删除连接
	 * @param eqid
	 * @return
	 */
	@RequestMapping("/delcon")
	public boolean doDelcon(int id) {
		
		return conservice.delconnect(id);
		
	}
	
	
	/**
	 * 分页查询连接表
	 * @param equ
	 * @param session
	 * @return
	 */
	@RequestMapping("/seacon")
	public PageInfo<Connect> searchEquip(Connect cond,int conpageNum,int conpageSize){
		
		return conservice.searchconnect(cond, conpageNum, conpageSize);
		
	}
	
	
	
	
}
