package com.hisir.picps.rpc;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hisir.picps.exception.BaseException;
import com.hisir.picps.exception.BaseResponse;

@Component
public class HisirOrderService {
	
	private static final Log log = LogFactory.getLog(HisirOrderService.class);
	
	@Autowired
	HisirOrderRpc hisirOrderRpc;
	
	public  String getPayOrderNo(Integer uid, Integer orderType, Integer currencyType, Integer payAmount) {
		try {
			log.info("uid:" + uid + ", orderType:" + orderType + ", currencyType:" + currencyType + ", payAmount:" + payAmount);
			BaseResponse<String> resp = hisirOrderRpc.getPayOrderNo(uid, orderType, currencyType, payAmount);
			log.info("uid:" + uid + ", orderType:" + orderType + ", currencyType:" 
			+ currencyType + ", payAmount:" + payAmount + ", payNo:" + resp.getData());
			return resp.getData();
		} catch (Exception e) {
			log.error("uid:" + uid + ", orderType:" + orderType + ", currencyType:" + currencyType 
					+ ", payAmount:" + payAmount + ", error:" + BaseException.getAllExInfo(e));
		}
		return "";
	}
	
	public  void setPayNoStatus(String payNo, Integer status) {
		try {
			log.info("payNo:" + payNo + ", status:" + status);
			hisirOrderRpc.setPayNoStatus(payNo, status);
		} catch (Exception e) {
			log.error("payNo:" + payNo + ", status:" + status + ", error:" + BaseException.getAllExInfo(e));
		}
	}
}
