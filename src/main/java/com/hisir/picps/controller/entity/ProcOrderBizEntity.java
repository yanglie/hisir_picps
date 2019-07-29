package com.hisir.picps.controller.entity;

import java.util.List;

import com.hisir.picps.mongo.entity.PicPsOrder;

public class ProcOrderBizEntity {
	public String order_no;
	public Long create_time;
	public List<UnProcTaskBizEntity> tasks;
	
	public ProcOrderBizEntity(PicPsOrder order) {
		order_no = order.getOrderNo();
		create_time = order.getCreateTime();
		tasks = UnProcTaskBizEntity.toProcTasks(order.getPsTasks());
	}
}
