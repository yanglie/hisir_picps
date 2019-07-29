package com.hisir.picps.controller.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hisir.picps.mongo.entity.PicPsTask;
import com.hisir.picps.mongo.entity.Picture;

@JsonInclude(Include.NON_NULL)
public class UnProcTaskBizEntity {
	public String task_id;
	public Integer task_status;
	public Integer ps_type;
	public List<String> picture_ids;
	public String param0;
	public String user_comment;
	public String worker_comment;
	
	public UnProcTaskBizEntity() {
		task_id = "";
		task_status = 0;
		ps_type = 0;
		picture_ids = new ArrayList<String>();
		param0 = "";
		user_comment = "";
		worker_comment = "";
	}
	
	public UnProcTaskBizEntity(PicPsTask task) {
		task_id = task.getTaskId();
		task_status = task.getTaskStatus();
		ps_type = task.getPsType();
		param0 = task.getParam0();
		user_comment = task.getUserComment();
		worker_comment = task.getWorkerComment();
		picture_ids = new ArrayList<String>();
		for (Picture pic: task.getSrcPics()) {
			picture_ids.add(pic.getPicId());
		}
	}
	
	static public List<UnProcTaskBizEntity> toProcTasks(List<PicPsTask> tasks) {
		List<UnProcTaskBizEntity> procTasks = new ArrayList<UnProcTaskBizEntity>();
		for (PicPsTask task: tasks) {
			procTasks.add(new UnProcTaskBizEntity(task));
		}
		return procTasks;
	}
	
}
