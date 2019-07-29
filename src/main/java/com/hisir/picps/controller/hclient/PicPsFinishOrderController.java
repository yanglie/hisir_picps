package com.hisir.picps.controller.hclient;

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
import com.hisir.picps.enums.PsStatusEnum;
import com.hisir.picps.exception.BaseException;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.messaging.PicPsGateway;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.service.PicPsOrderService;


@RestController
public class PicPsFinishOrderController {

	private static final Log log = LogFactory.getLog(PicPsFinishOrderController.class);

	@Autowired
	PicPsOrderService picPsOrderService;
	@Autowired
	PicPsGateway picPsGateway;
	
	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_finish_order", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Integer> picPsFinishOrder(
			@RequestParam(value = "principal_id", required = true) Integer uid,
			@RequestParam(value = "order_no", required = true) String orderNo) {
		
		log.info("uid:" + uid + ", orderNo:" + orderNo);
		try {
			PicPsOrder order = picPsOrderService.findByUidAndOrderNo(uid, orderNo);
			if (order == null) {
				return new BaseResponse<Integer>(ErrCodeEnum.M03_INVALID_ORDER_NO);
			}
			order.setPsStatus(PsStatusEnum.ACCEPT.getIdx());
			picPsOrderService.save(order);
		} catch (Exception e) {
			log.error("uid:" + uid + ", orderNo:" + orderNo + ", error:" + BaseException.getAllExInfo(e));
			return new BaseResponse<Integer>(ErrCodeEnum.SYS_ERR_UNKNOWN);
		}
		return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
	}
	
}