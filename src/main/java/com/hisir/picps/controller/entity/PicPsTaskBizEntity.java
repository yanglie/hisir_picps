package com.hisir.picps.controller.entity;

import java.util.ArrayList;
import java.util.List;

import com.hisir.picps.mongo.entity.PicPsTask;

public class PicPsTaskBizEntity {
	
	public String task_id;
	public Integer task_status;
	public Integer ps_type;
	public String param0;
	public String user_comment;
	public String worker_comment;
	public List<PictureBizEntity> src_pics;
	public PictureBizEntity des_pic;
	
	public PicPsTaskBizEntity(PicPsTask task) {
		task_id = task.getTaskId();
		task_status = task.getTaskStatus();
		ps_type = task.getPsType();
		param0 = task.getParam0();
		user_comment = task.getUserComment();
		worker_comment = task.getWorkerComment();
		src_pics = PictureBizEntity.toPics(task.getSrcPics());
		des_pic = new PictureBizEntity(task.getDesPic());
	}
	
	static public List<PicPsTaskBizEntity> toTasks(List<PicPsTask> tasks) {
		List<PicPsTaskBizEntity> tasksBiz = new ArrayList<PicPsTaskBizEntity>();
		for (PicPsTask task: tasks) {
			tasksBiz.add(new PicPsTaskBizEntity(task));
		}
		return tasksBiz;
	}
}
