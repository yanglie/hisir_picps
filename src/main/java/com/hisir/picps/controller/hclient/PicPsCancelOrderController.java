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
import com.hisir.picps.enums.PayStatusEnum;
import com.hisir.picps.exception.BaseException;
import com.hisir.picps.exception.BaseResponse;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.service.PicPsOrderService;


@RestController
public class PicPsCancelOrderController {

	private static final Log log = LogFactory.getLog(PicPsCancelOrderController.class);

	@Autowired
	PicPsOrderService picPsOrderService;
	
	@ApiVersion(min = 1.000, max = 1.001)
	@RequestMapping(value = "/picps/picps_cancel_order", method = RequestMethod.POST)
	public @ResponseBody BaseResponse<Integer> picPsCancelOrder(
			@RequestParam(value = "principal_id", required = true) Integer uid,
			@RequestParam(value = "order_no") String orderNo,
			@RequestParam(value = "pay_status") Integer pay_status) {
		
		log.info("uid:" + uid + ", orderNo:" + orderNo + ", order_status:" + pay_status);
		try {
			PicPsOrder order = picPsOrderService.findByUidAndOrderNo(uid, orderNo);
			if (order == null) {
				log.error("uid:" + uid + ", orderNo:" + orderNo + " is null");
				return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
			}
			if (pay_status == PayStatusEnum.CANCEL.getIdx()	|| pay_status == PayStatusEnum.DELETE.getIdx()){
				order.setPayStatus(pay_status);
				order.setModifyTime(new Date().getTime());
				picPsOrderService.save(order);
			} else {
				log.error("uid:" + uid + ", orderNo:" + orderNo + ", pay_status:" + pay_status + ", order status Error");
			}
		} catch (Exception e) {
			log.error("uid:" + uid + ", orderNo:" + orderNo + BaseException.getAllExInfo(e));
			return new BaseResponse<Integer>(ErrCodeEnum.SYS_ERR_UNKNOWN);
		}
		return new BaseResponse<Integer>(ErrCodeEnum.SUCCESS);
	}
	
}