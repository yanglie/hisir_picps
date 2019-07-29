package com.hisir.picps.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hisir.picps.exception.BaseResponse;

@FeignClient("hisir-order")
public interface HisirOrderRpc {

	@RequestMapping(value = "/get_pay_order_no", method = RequestMethod.POST)
	@ResponseBody BaseResponse<String> getPayOrderNo(
			@RequestParam(value = "uid", required = true) Integer uid,
			@RequestParam(value = "order_type", required = true) Integer orderType,
			@RequestParam(value = "currency_type", required = true) Integer currencyType,
			@RequestParam(value = "pay_amount", required = true) Integer payAmount);
	
	@RequestMapping(value = "/set_pay_no_status", method = RequestMethod.POST)
	@ResponseBody BaseResponse<Integer> setPayNoStatus(
			@RequestParam(value = "pay_no", required = true) String payNo,
			@RequestParam(value = "status", required = true) Integer status);
}
