package com.platformmake.model.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.platformmake.model.dao.TrackinfoMapper;
import com.platformmake.model.entity.Productinfo;
import com.platformmake.model.entity.Trackinfo;
import com.platformmake.model.entity.Workinfo;
import com.platformmake.model.services.TrackService;
import com.platformmake.model.services.ProductService;

@RestController//锟斤拷锟斤拷锟斤拷锟斤拷锟叫凤拷锟斤拷锟斤拷锟斤拷锟?	@ResponseBody
public class TrackController {

		@Autowired
		private TrackService track;
		
		@Autowired
		private ProductService product;
		
		/**
		 * 通鍒嗛〉鏌ヨ
		 * @param cond
		 * @param pageNum
		 * @param pageSize
		 * @return
		 */
		@RequestMapping("/checktrack")
		public PageInfo<Trackinfo> doSearchTrack(Trackinfo cond, int trackpageNum, int trackpageSize) {			
		return track.PageSearchtrackInfo(cond, trackpageNum, trackpageSize);
		}
		
		/**
		 * 娣诲姞璺熻釜
		 * @param work
		 * @return
		 */
		
		@RequestMapping("/dotrack")
		public Boolean doTrackWord(int workid) {
			return track.addTrack( workid );
		}
		
		/**
		 * 瀹屾垚璺熻釜
		 * @param workid
		 * @return
		 */
		@RequestMapping("/finishtrack")
		public boolean doFinish(int id) {
			return track.finishTrack(id);
		}
		/**
		 * 鍒犻櫎璺熻釜
		 * @param workid
		 * @return
		 */
		@RequestMapping("/deltrack")
		public boolean doDelectTrack(int id) {
			return track.delectTrack(id);
			
		}
		/**
		 * 鏇存柊璺熻釜
		 * @param tra
		 * @return
		 */
		@RequestMapping("/updatetrack")
		public boolean doupdateTrack(Trackinfo tra) {
			return track.updataTrack(tra);
			
		}
		
		
}
