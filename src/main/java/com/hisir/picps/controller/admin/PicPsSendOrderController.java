package com.hisir.picps.controller.admin;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hisir.picps.enums.ErrCodeEnum;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.messaging.PicPsGateway;


@RestController
public class PicPsSendOrderController {

	private static final Log log = LogFactory.getLog(PicPsSendOrderController.class);

	@Autowired
	PicPsGateway picPsGateway;
	
	/*
	 * 手工发送到图片处理中心处理
	 */
	@RequestMapping(value = "/inpicps/picps_send_order", method = RequestMethod.GET)
	public @ResponseBody BaseResponse<Integer> picPsUndoOrder(
			@RequestParam(value = "order_no", required = true) String orderNo) {
		log.info("orderNo:" + orderNo);
		picPsGateway.sendNewOrderNo(orderNo);
		return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
	}
	

}