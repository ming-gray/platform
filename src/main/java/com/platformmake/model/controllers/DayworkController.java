package com.platformmake.model.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.entity.DayWork;
import com.platformmake.model.entity.Trackinfo;
import com.platformmake.model.services.DayworkService;

@RestController//该类下所有方法都添加	@ResponseBody
public class DayworkController {
	
	@Autowired
	private DayworkService dayservice; 
	
	@RequestMapping("/checkdaiwork")
	public PageInfo<DayWork> doSearchTrack(DayWork cond, int dayworkpageNum, int dayworkpageSize) {
		return dayservice.searchDaiwork(cond, dayworkpageNum, dayworkpageSize);
	}
	
	@RequestMapping("/adddaiwork")
	public boolean doAddWork(DayWork daywork){
		return dayservice.addDaiwork(daywork);
	}
}
