package com.hisir.picps.controller.entity;

import java.util.ArrayList;
import java.util.List;

import com.hisir.picps.mongo.entity.PicPsPriority;

public class PicPsPriorityBizEntity {
	
	public Integer bitint;
	public String name;
	public Integer priority;
	
	
	public PicPsPriorityBizEntity(PicPsPriority psPriority) {
		bitint = psPriority.getBitInt();
		name = psPriority.getName();
		priority = psPriority.getPriority();
	}
	
	static public List<PicPsPriorityBizEntity> toBizs(List<PicPsPriority> psPrioritys) {
		List<PicPsPriorityBizEntity> tasksBiz = new ArrayList<PicPsPriorityBizEntity>();
		for (PicPsPriority psPriority: psPrioritys) {
			tasksBiz.add(new PicPsPriorityBizEntity(psPriority));
		}
		return tasksBiz;
	}
}
