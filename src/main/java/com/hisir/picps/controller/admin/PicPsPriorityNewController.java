package com.hisir.picps.controller.admin;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsPriority;
import com.hisir.picps.mongo.service.PicPsPriorityService;

@RestController
public class PicPsPriorityNewController {

	//private static final Log log = LogFactory.getLog(PicPsQueryPriorityController.class);
	
	@Autowired
	PicPsPriorityService picPsPriorityService;
	
	@RequestMapping(value = "/inpicps/picps_new_priority", method = RequestMethod.GET)
	public @ResponseBody BaseResponse<Integer> picPsNewPriority(
			@RequestParam(value = "bitint", required = true) Integer bitInt,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "priority", required = true) Integer priority) {
		
		PicPsPriority entity = new PicPsPriority();
		entity.setBitInt(bitInt);
		entity.setName(name);
		entity.setPriority(priority);
		entity.setStatus(PicPsPriority.STATUS_VALID);
		entity.setModifyTime(new Date().getTime());
		
		picPsPriorityService.save(entity);
		
		return new BaseResponse<>(ErrCodeEnum.SUCCESS);
	}
	

}