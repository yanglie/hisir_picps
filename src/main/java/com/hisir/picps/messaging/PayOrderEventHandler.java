package com.hisir.picps.messaging;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import com.hisir.picps.enums.PayStatusEnum;
import com.hisir.picps.enums.PsStatusEnum;
import com.hisir.picps.exception.BaseException;
import com.hisir.picps.mongo.entity.PicPsOrder;
import com.hisir.picps.mongo.service.PicPsOrderService;

@Component
public class PayOrderEventHandler {

	private static Log log = LogFactory.getLog(PayOrderEventHandler.class);
	
	@Autowired
	PicPsOrderService picPsOrderService;
	@Autowired
	PicPsGateway picPsGateway;
	
	@StreamListener(PayOrderSink.INPUT)
	public void handler(PayOrderInfo payOrderInfo) {
		try {
			log.info(payOrderInfo.toString());
			
			if (payOrderInfo.getOrderType() == 4) {
				PicPsOrder order = picPsOrderService.findByUidAndOrderNo(payOrderInfo.getUid(), payOrderInfo.getPayNo());
				order.setPayStatus(payOrderInfo.getStatus());
				order.setModifyTime(new Date().getTime());
				if (payOrderInfo.getStatus() == PayStatusEnum.SUCC.getIdx()) {
					order.setPsStatus(PsStatusEnum.NOPIC.getIdx());
					picPsGateway.sendNewOrderNo(order.getOrderNo());
				}
				picPsOrderService.save(order);
			}
			
		} catch (Exception e) {
			log.error(payOrderInfo.toString() + ", error:" + BaseException.getAllExInfo(e));
		}
	}

}
