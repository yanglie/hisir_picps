package com.hisir.picps.controller.entity;

import java.util.List;

public class PicPsOrderBizEntity {
	public String order_no = "";
	public String title;
	public String remark;
	public Integer currency_type;
	public Integer pay_amount;
	public Integer pay_status;
	public Integer ps_status;
	public List<PicPsTaskBizEntity> tasks;
	public Long create_time;
}
