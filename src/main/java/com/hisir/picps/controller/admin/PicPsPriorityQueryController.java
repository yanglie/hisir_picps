package com.hisir.picps.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.controller.entity.PicPsPriorityBizEntity;
import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsPriority;
import com.hisir.picps.mongo.service.PicPsPriorityService;

@RestController
public class PicPsPriorityQueryController {

	//private static final Log log = LogFactory.getLog(PicPsQueryPriorityController.class);
	
	@Autowired
	PicPsPriorityService picPsPriorityService;
	
	@RequestMapping(value = "/inpicps/picps_query_priority", method = RequestMethod.GET)
	public @ResponseBody BaseResponse<List<PicPsPriorityBizEntity>> picPsQueryPriority() {
		List<PicPsPriority> entities= picPsPriorityService.findByStatus(PicPsPriority.STATUS_VALID);
		
		BaseResponse<List<PicPsPriorityBizEntity>> resp = new BaseResponse<List<PicPsPriorityBizEntity>>(ErrCodeEnum.SUCCESS);
		resp.setData(PicPsPriorityBizEntity.toBizs(entities));
		return resp;
	}

}