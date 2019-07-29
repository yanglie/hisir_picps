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
public class PicPsPrioritySetController {

	//private static final Log log = LogFactory.getLog(PicPsQueryPriorityController.class);
	
	@Autowired
	PicPsPriorityService picPsPriorityService;
	
	@RequestMapping(value = "/inpicps/picps_set_priority", method = RequestMethod.GET)
	public @ResponseBody BaseResponse<Integer> picPsNewPriority(
			@RequestParam(value = "bitint", required = true) Integer bitInt,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "priority", required = false) Integer priority,
			@RequestParam(value = "status", required = false) Integer status) {
		
		PicPsPriority entity = picPsPriorityService.findByBitInt(bitInt);
		if (entity != null) {
			if (name != null) {
				entity.setName(name);
			}
			if (priority != null) {
				entity.setPriority(priority);
			}
			if (status != null) {
				entity.setStatus(status);
			}
			entity.setModifyTime(new Date().getTime());
			picPsPriorityService.save(entity);
		}	
		return new BaseResponse<>(ErrCodeEnum.SUCCESS);
	}
	

}