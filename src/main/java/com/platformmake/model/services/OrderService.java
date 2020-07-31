/**
 * 璁㈠崟绠＄悊妯″潡
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
	 * 转成生产计划
	 * @param plan
	 * @return
	 */
	public boolean turnToPlan(Planinfo plan) {
		Orderinfo ord = orderMapper.selectByPrimaryKey(plan.getOrdid());
		//订单已接单
		if(ord.getOrdstate()==20) {
			plan.setPlanstate(10);
			plan.setOrdid(ord.getOrdid());
			plan.setPlancount(ord.getProordnum());
			plan.setProid(ord.getProid());
			pla.insert(plan);
			//新建计划后，将订单状态改为生产中
			ord.setOrdstate(40);
			orderMapper.updateByPrimaryKeySelective(ord);
			return true;
		}
			return false;
	}
	/**
	 * 鏌ヤ笅鎷夋
	 * @return
	 */
	public List<Productinfo> searchAllPro() {
		return pro.selectByExample(null);
	}
	/**
	 * 鏂板缓璁㈠崟
	 * 
	 */
	public boolean addOrder(Orderinfo ord) {
		//鍒ゆ柇浜у搧鍚嶆槸鍚﹀悎娉�
		ProductinfoExample example = new ProductinfoExample();
		com.platformmake.model.entity.ProductinfoExample.Criteria cc = example.createCriteria();
		//濡傛灉涓嶅瓨鍦ㄧ浉鍚屽悕绉扮殑浜у搧鍒欎笉鍚堟硶锛屾棤娉曟柊寤鸿鍗�
		cc.andProidEqualTo(ord.getProid());
		
		List<Productinfo> list=pro.selectByExample(example);
		if(list.size()>0) {
			//浜у搧鍚嶅悎娉�
			orderMapper.insert(ord);
			ord.setOrdstate(10);
			return true;
		}else {
			//浜у搧鍚嶄笉鍚堟硶
			return false;
		}
	}
	/**
	 * 鎺ュ彈璁㈠崟
	 * 
	 */
	public boolean acceptOrder(Orderinfo ord){
		//璁＄畻宸ュ巶浜ц兘
		//璁＄畻鏃堕棿宸�
		SimpleDateFormat days = new SimpleDateFormat("yyyy-MM-dd");
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
		times =new Date();
		int timebreak=0;
		int timebreaks_m = ord.getOrddl().getMonth()-times.getMonth();
		int timebreaks_d=ord.getOrddl().getDate()-new Date().getDate();
		if(timebreaks_m==0) {
			timebreak =(int)(timebreaks_m*30+timebreaks_d);
		}else{
			//if(timebreaks_d>0) {
				timebreak =(int)(timebreaks_m*30+timebreaks_d);
			//}else {
				//timebreak =(int)((timebreaks_m+1)*30+timebreaks_d);
			//}
		}
		System.out.println(ord.getOrddl());
		System.out.println(times);
		System.out.println(timebreaks_m);
		System.out.println(timebreaks_d );
		System.out.println(timebreak);
		//long days = (ord.getOrddl().getTime() - new Date().getTime() + 1000000)/(60*60*24*1000);
		//鍦ㄨ澶囦骇鍝佽〃涓煡璇㈣兘鐢熶骇璇ヤ骇鍝佺殑鍏ㄩ儴璁惧鐨勪骇鑳�
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
            // 鍙帴鍗� 鐘舵�佹洿鏂颁负宸叉帴鍗曠姸鎬�
        	ord.setOrdstate(20);
        	ord.setApc(totalapc);
        	orderMapper.updateByPrimaryKey(ord);
        	//杞垚鐢熶骇璁″垝
            return true;
		}else {
			ord.setApc(totalapc);
        	orderMapper.updateByPrimaryKey(ord);
		}
		return false;
	}
	/**
	 * 鎷掔粷璁㈠崟
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
	 * 淇敼璁㈠崟鐘舵��
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
	 * 瀹屾垚璁㈠崟
	 * @param ord
	 * @return
	 */
	public boolean finishOrder(Orderinfo ord){
		//鏌ヨ璇ヨ鍗曠殑鐘舵�佹槸鍚︿负鐢熶骇涓�
		if(ord.getOrdstate()==40) {
			//鏌ヨ璇ヨ鍗曠殑鐢熶骇璁″垝鐘舵�佹槸鍚︿负宸插畬鎴�
			PlaninfoExample example = new PlaninfoExample();
			com.platformmake.model.entity.PlaninfoExample.Criteria cc = example.createCriteria();
			cc.andOrdidEqualTo(ord.getOrdid());
			
			List<Planinfo> plans= pla.selectByExample(example);
			for(Planinfo pla:plans) {
				if(pla.getPlanstate()!=30)
					return false;
			}
			int nums=ord.getProordnum();
			int num=Math.abs(nums);
			ord.setOrdstate(50);
			ord.setQuacom(num);
			orderMapper.updateByPrimaryKey(ord);
		}
		return true;
	}
	/**
	 * 鍒犻櫎璁㈠崟
	 * @param ordid
	 * @return
	 */
	public boolean deleteByOrderid(int ordid){
		//澶勪簬宸叉帴鍗曞拰鐢熶骇涓殑璁㈠崟涓嶈兘鍒犻櫎
		Orderinfo ord=orderMapper.selectByPrimaryKey(ordid);
		if(ord.getOrdstate()==20 || ord.getOrdstate() == 40)
			return false;
			int i=orderMapper.deleteByPrimaryKey(ordid);
			return i>0;
	}
	/**
	 * 淇敼璁㈠崟
	 * @param ord
	 * @return
	 */
	public boolean modOrderbyid(Orderinfo ord) {
		ProductinfoExample example = new ProductinfoExample();
		com.platformmake.model.entity.ProductinfoExample.Criteria cc = example.createCriteria();
		cc.andProidEqualTo(ord.getProid());
		
		List<Productinfo> list = pro.selectByExample(example);
		//浜у搧鍚嶅悎娉�
		if(list.size()>0) {
			//鏁伴噺姝ｇ‘
			if(ord.getProordnum()>=0&&ord.getQuacom()>=0&&ord.getApc()>=0)
				//鎴鏃堕棿鍚堢悊
			//	if(new Date().before(ord.getOrddl()))
				//{
					orderMapper.updateByPrimaryKey(ord);
					return true;
			//	}
		}
		return false;
	}
	/**
	 * 鍒嗛〉鏌ヨ
	 * @param ord
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<Orderinfo> searchOrder(Orderinfo ord,int pageNum, int pageSize){
		OrderinfoExample example = new OrderinfoExample();
		com.platformmake.model.entity.OrderinfoExample.Criteria cc= example.createCriteria();
		if(null != ord.getOrdid()) {
			//娣诲姞璁㈠崟鍙锋煡璇㈡潯浠�
			cc.andOrdidEqualTo(ord.getOrdid());
		}
		if(null != ord.getProid()) {
			//娣诲姞浜у搧鍚嶆煡璇㈡潯浠�
			cc.andProidEqualTo(ord.getProid());
		}
		if(null!= ord.getOrdstate()) {
			//娣诲姞璁㈠崟鐘舵�佹煡璇㈡潯浠�
			cc.andOrdstateEqualTo(ord.getOrdstate());
		}
		//鍚姩鍒嗛〉鎻掍欢
		PageHelper.startPage(pageNum,pageSize);
		//瀹炴柦鏌ヨ
		List<Orderinfo> list =orderMapper.selectByExample(example);
		//杩斿洖鍊�
		return new PageInfo<Orderinfo>(list);
}
}