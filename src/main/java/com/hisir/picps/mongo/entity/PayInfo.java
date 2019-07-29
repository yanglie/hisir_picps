//package com.hisir.picps.mongo.entity;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//public class PayInfo implements Serializable {
//	
//	private static final long serialVersionUID = 1L;
//	
//	@Field("currency_type")
//	private Integer currencyType;
//	
//	@Field("fee")
//	private Integer fee;
//	
//	@Field("pay_account")
//	private String payAccount;
//	
//	@Field("pay_method")
//	private Integer payMethod;
//	
//	@Field("tp_order_no")
//	private String tpOrderNo;
//	
//	@Field("pay_time")
//	private Long payTime;
//	
//	public PayInfo() {
//		currencyType = 1;
//		fee = 1;
//		payAccount = "";
//		payMethod = 1;
//		tpOrderNo = "";
//		payTime = (long) 0;
//	}
//
//	public Integer getCurrencyType() {
//		return currencyType;
//	}
//
//	public void setCurrencyType(Integer currencyType) {
//		this.currencyType = currencyType;
//	}
//
//	public Integer getFee() {
//		return fee;
//	}
//
//	public void setFee(Integer fee) {
//		this.fee = fee;
//	}
//
//	public String getPayAccount() {
//		return payAccount;
//	}
//
//	public void setPayAccount(String payAccount) {
//		this.payAccount = payAccount;
//	}
//
//	public Integer getPayMethod() {
//		return payMethod;
//	}
//
//	public void setPayMethod(Integer payMethod) {
//		this.payMethod = payMethod;
//	}
//
//	public String getTpOrderNo() {
//		return tpOrderNo;
//	}
//
//	public void setTpOrderNo(String tpOrderNo) {
//		this.tpOrderNo = tpOrderNo;
//	}
//
//	public Long getPayTime() {
//		return payTime;
//	}
//
//	public void setPayTime(Long payTime) {
//		this.payTime = payTime;
//	}
//	
//	
//}
