package com.hisir.picps.messaging;

public class PayOrderInfo {
	private Integer uid;
	private String payNo;
	private Integer orderType;
	private Integer status;
	
	@Override
	public String toString() {
		return String.format("uid:%s, payNo:%s, orderType:%s, status:%s", uid, payNo, orderType, status);
	}
		
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getPayNo() {
		return payNo;
	}
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
