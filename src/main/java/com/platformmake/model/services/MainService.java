package com.platformmake.model.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.platformmake.model.dao.EquipinfoMapper;
import com.platformmake.model.dao.OrderinfoMapper;

@Service
public class MainService {

	@Autowired
	private EquipinfoMapper em;
	
	@Autowired
	private OrderinfoMapper om;
	
	public Map<String, Object> statisticEq(){
		 try {
			//总数量、开机数量、运行数量、故障数量
			int total, avaiNum, runNum, failNum;
			
			float avaiRate = 0, runRate = 0, failRate = 0;
			
			total = em.statisticsEq(0);
			if (total != 0) {
				avaiNum = total - em.statisticsEq(20);
				runNum = em.statisticsEq(10);
				failNum = em.statisticsEq(30);	
				avaiRate = (float) (avaiNum * 1.0 / total);
				runRate = (float) (runNum * 1.0 / total);
				failRate = (float) (failNum * 1.0 / total);
			}
			Map<String, Object> map = new HashMap<>();
	        map.put("avaiRate", avaiRate);
	        map.put("runRate", runRate);
	        map.put("failRate", failRate);
	        System.out.println(map);
	        return map;
	        } catch (Exception e) {
	            e.printStackTrace();
	            System.out.println("失败");
	            return null;
	        }		
	}
}
