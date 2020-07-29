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

/**
 * 
 * 
 * @return
 */

@RestController
public class EquipinfoController {
	
	private static final String CURRENT_EQUIP = "CURRENTEQU";
	@Autowired
	private EquipinfoService service;
	
	/**
	 * 修改设备
	 * @param equ
	 * @return
	 */
	@RequestMapping("/modequip")
	public boolean doModEquip(Equipinfo equ,Connect con) {
		return service.modEquip(equ, con);
	}
	
	/**
	 * 删除用户
	 * @param eqid
	 * @return
	 */
	@RequestMapping("/delequip")
	public boolean doDelEquip(int eqid) {
		return service.delEquip(eqid);
	}
	/**
	 * 设备id查找设备
	 * @param equ
	 * @param session
	 * @return
	 */
	@RequestMapping("/seaequipbyeqid")
	public PageInfo<Equipinfo> searchEquip(Equipinfo cond,Connect con,Integer pageNum,Integer pageSize){
		return service.searchEquip(cond, con, pageNum, pageSize);
	}
	
	@RequestMapping("/seabyproid")
	public PageInfo<Connect> searchEquipByProid(Connect con,Integer pageNum,Integer pageSize){
		return service.searchEquipByProid(con, pageNum, pageSize);
	}
	
	@RequestMapping("/addequip")
	public boolean doAddEquip(Equipinfo equ,Connect con) {
		return service.addEquip(equ, con);
		
	}

	/**
	 *
	 * @param equ
	 * @param session
	 * @return
	 */
	@PostMapping("/login")
	public Equipinfo doLogin(Equipinfo equ, HttpSession session) {
		Equipinfo result = service.checkLogin(equ);
		if(null != result) {
		
			session.setAttribute("CURRENTEQU", result);
			return result;
		}
		else {
			return null;
		}
	}
	
	@InitBinder
    protected void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
