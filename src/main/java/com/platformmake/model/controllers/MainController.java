package com.platformmake.model.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.platformmake.model.services.MainService;

@RestController
public class MainController {

	@Autowired
	private MainService service;
	
	@RequestMapping("/staeq")
	public Map<String, Object> doStatisticEq(){
		return service.statisticEq();
	}
	
	@RequestMapping("/staorder")
	public Map<String, List<Object>> doStatisticOrdstate(){
		return service.statisticOrdstate();
	}
	
	@RequestMapping("/staorderbymonth")
	public Map<String, List<Object>> doStatisticOrderByMonth(){
		return service.statisticOrderByMonth();
	}
}
