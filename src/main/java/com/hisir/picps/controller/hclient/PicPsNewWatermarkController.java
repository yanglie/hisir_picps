package com.hisir.picps.controller.hclient;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.common.ApiVersion;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsWaterPicInfo;
import com.hisir.picps.mongo.entity.PicPsWatermark;
import com.hisir.picps.mongo.entity.Picture;
import com.hisir.picps.mongo.service.PicPsWatermarkService;


@RestController
public class PicPsNewWatermarkController {

	private static final Log log = LogFactory.getLog(PicPsNewWatermarkController.class);
	
	@Autowired
	PicPsWatermarkService picWatermarkService;

	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_new_watermark", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Integer> picPsNewOrder(
			@RequestParam(value = "principal_id", required = true) Integer uid,
			@RequestParam(value = "pic", required = false) String pic,
			@RequestParam(value = "text", required = false) String text,
			@RequestParam(value = "comment", required = true) String comment) {
		
		log.info("uid:" + uid + ", text:" + text + ", pic:" + pic);
		
		Long nowTs = new Date().getTime();
		
		PicPsWatermark entity = picWatermarkService.findByUid(uid);
		if (entity == null) {
			entity = new PicPsWatermark(uid);
			entity.setModifyTime(nowTs);
		}
		if (text != null && ! text.equals("")) {
			entity.getWaterTxt().put(text, comment);
		}
		if (pic != null && ! pic.equals("")) {
			JSONObject jsonObj = new JSONObject(pic);
			String name = null;
			Picture picture = null;
			if (jsonObj.has("pic_name")) {
				name = jsonObj.getString("pic_name");
			}
			if (jsonObj.has("pic_info")) {
				picture = new Picture(jsonObj.getJSONObject("pic_info"));
			}
			if (name != null && ! name.equals("") 
					&& picture != null && ! picture.getPicId().equals("")) {
				entity.getWaterPic().add(new PicPsWaterPicInfo(name, comment, picture));
			}
		}
		picWatermarkService.save(entity);
		
		return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
	}
	

}