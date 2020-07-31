package com.platformmake.model.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
				avaiNum = total - em.statisticsEq(30);
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
	
	public Map<String, List<Object>> statisticOrdstate(){
		try {
			List<Map<String,Object>> list = om.statisticOrder();
			System.out.println(list);
			Integer[] nums = new Integer[5];
			Arrays.fill(nums, 0);
			for (Map<String, Object> map : list) {
                int status = (int) map.get("status");
                int num = ((Long) map.get("num")).intValue();
                nums[status / 10 - 1] = num;
            }
			Map<String, List<Object>> res = new HashMap<>();
			List<Object> names = Arrays.asList("待接单", "已接单", "已排产", "生产中", "已完成");
	        List<Object> rate = Arrays.asList(nums);
	        res.put("name", names);
	        res.put("value", rate);
	        System.out.println(res);
	        return res;
		}
		catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
            return null;
        }
	}
	
	public Map<String, List<Object>> statisticOrderByMonth(){
		try {
			List<Map<String, Object>> list = om.statisticOrderByMonth();
			System.out.println(list);
			Integer[] nums = new Integer[12];
	        Arrays.fill(nums, 0);
	        for (Map<String, Object> map : list) {
	             int month = (int) map.get("month");
	             int num = ((Long) map.get("num")).intValue();
	             nums[month - 1] = num;
	         }
	        Map<String, List<Object>> res = new HashMap<>();
	        List<Object> months = new ArrayList<>();
	        for (int i = 0; i < 12; i++) {
	             months.add((i + 1) + "月");
	        }
	        List<Object> num = Arrays.asList(nums);
	        res.put("month", months);
	        res.put("num", num);
	        System.out.println(res);
	        return res;
		}
		catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败");
            return null;
        }
	}
}
