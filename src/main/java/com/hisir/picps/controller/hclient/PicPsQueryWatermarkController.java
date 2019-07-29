package com.hisir.picps.controller.hclient;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.common.ApiVersion;
import com.hisir.picps.controller.entity.PictureBizEntity;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsWaterPicInfo;
import com.hisir.picps.mongo.entity.PicPsWatermark;
import com.hisir.picps.mongo.service.PicPsWatermarkService;


@RestController
public class PicPsQueryWatermarkController {

	private static final Log log = LogFactory.getLog(PicPsQueryWatermarkController.class);
	
	@Autowired
	PicPsWatermarkService picWatermarkService;

	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_query_watermark", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Map<String, Object>> picPsNewOrder(
			@RequestParam(value = "principal_id", required = true) Integer uid) {
		
		log.info("uid:" + uid);
		
		PicPsWatermark entity = picWatermarkService.findByUid(uid);
		BaseResponse<Map<String, Object>> resp = new BaseResponse<Map<String, Object>>(ErrCodeEnum.SUCCESS);
		if (entity != null) {
			Map<String, Object> mapData = new HashMap<String, Object>();
			
			List<TextBizData> textsbizList = new ArrayList<TextBizData>();
			for (String text: entity.getWaterTxt().keySet()) {
				TextBizData textbiz = new TextBizData();
				textbiz.text = text;
				textbiz.comment = entity.getWaterTxt().get(text);
				textsbizList.add(textbiz);
			}
			mapData.put("texts", textsbizList);
			
			List<PicsBizData> picsbizList = new ArrayList<PicsBizData>();
			for (PicPsWaterPicInfo picInfo: entity.getWaterPic()) {
				PicsBizData picsbiz = new PicsBizData();
				picsbiz.pic_uuid = picInfo.getPicUuid();
				picsbiz.pic_name = picInfo.getPicName();
				picsbiz.comment = picInfo.getComment();
				picsbiz.pic_info = new PictureBizEntity(picInfo.getPicInfo());
				picsbizList.add(picsbiz);
			}
			mapData.put("pics",picsbizList);
			resp.setData(mapData);
		}
		return resp;
	}
	
	public class TextBizData {
		public String text;
		public String comment;
	}
	
	public class PicsBizData {
		public String pic_uuid;
		public String pic_name;
		public String comment;
		public PictureBizEntity pic_info;
	}
	

}