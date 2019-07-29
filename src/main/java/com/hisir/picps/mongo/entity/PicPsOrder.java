package com.hisir.picps.mongo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class PicPsOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
			
	/*
	 * picType字段为图片处理类型，可多选
	 *   0x00000001 // 单图处理
	 *   0x00000002 // 多图合成
	 */
//	public static final int PIC_TYPE_1 = 0x00000001;
//	public static final int PIC_TYPE_2 = 0x00000002;
	
	//@GeneratedValue
	@Id
	private String id;
	
	@Indexed
	@Field("uid")
	private Integer uid;

	//@GeneratedValue
	@Indexed
	@Field("order_no")
	private String orderNo = "";
	
	@Field("title")
	private String title;
	
	@Field("remark")
	private String remark;
	
	//货币类型
	@Field("currency_type")
	private Integer currencyType;
	//支付金额，单位为分
	@Field("pay_amount")
	private Integer payAmount;
	
	@Field("pay_status")
	private Integer payStatus;
	
	@Field("ps_status")
	private Integer psStatus;
	
	@Field("ps_tasks")
	private List<PicPsTask> psTasks;

	@Field("create_time")
	private Long createTime;
	
	@Field("modify_time")
	private Long modifyTime;
		
	
	public PicPsOrder() {
		psTasks = new ArrayList<PicPsTask>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(Integer currencyType) {
		this.currencyType = currencyType;
	}

	public Integer getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPsStatus() {
		return psStatus;
	}

	public void setPsStatus(Integer psStatus) {
		this.psStatus = psStatus;
	}

	public List<PicPsTask> getPsTasks() {
		return psTasks;
	}

	public void setPsTasks(List<PicPsTask> psTasks) {
		this.psTasks = psTasks;
	}


	
}
