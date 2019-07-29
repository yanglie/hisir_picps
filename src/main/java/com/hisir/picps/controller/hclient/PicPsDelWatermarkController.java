package com.hisir.picps.controller.hclient;


import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.hisir.picps.mongo.service.PicPsWatermarkService;


@RestController
public class PicPsDelWatermarkController {

	private static final Log log = LogFactory.getLog(PicPsDelWatermarkController.class);
	
	@Autowired
	PicPsWatermarkService picWatermarkService;

	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_del_watermark", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Integer> picPsNewOrder(
			@RequestParam(value = "principal_id", required = true) Integer uid,
			@RequestParam(value = "pic_uuid", required = false) String picUuid,
			@RequestParam(value = "text", required = false) String text) {
		
		log.info("uid:" + uid + ", picUuid:" + picUuid + ", text:" + text);
		
		Long nowTs = new Date().getTime();
		
		PicPsWatermark entity = picWatermarkService.findByUid(uid);
		if (entity == null) {
			entity = new PicPsWatermark(uid);
			entity.setModifyTime(nowTs);
		}
		if (text != null && entity.getWaterTxt() != null) {
			if (entity.getWaterTxt().containsKey(text)) {
				entity.getWaterTxt().remove(text);
			}
		}
		if (picUuid != null && entity.getWaterPic() != null) {
			for (PicPsWaterPicInfo picInfo: entity.getWaterPic()) {
				if (picInfo.getPicUuid().equals(picUuid)) {
					entity.getWaterPic().remove(picInfo);
					break;
				}
			}
		}
		picWatermarkService.save(entity);
		
		return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
	}
	

}