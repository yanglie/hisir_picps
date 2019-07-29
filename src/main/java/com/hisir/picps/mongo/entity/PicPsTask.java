package com.hisir.picps.mongo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Field;

import com.hisir.picps.enums.TaskStatusEnum;

public class PicPsTask implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static final String JSON_KEY_PS_TYPE  = "ps_type";
	static final String JSON_KEY_PARAM0  = "param0";
	static final String JSON_KEY_COMMENT0 = "user_comment";
	static final String JSON_KEY_COMMENT1 = "worker_comment";
	static final String JSON_KEY_PICS = "pictures";
	
	//@Id
	@Field("task_id")
	private String taskId;
	
	@Field("task_status")
	private Integer taskStatus;
	
	@Field("ps_type")
	private Integer psType;
	
	@Field("param0")
	private String param0;    //水印text
	
	@Field("user_comment")
	private String userComment;  //用户评论
	
	@Field("worker_comment")
	private String workerComment;  //work评论
	
	@Field("src_pics")
	private List<Picture> srcPics;
	
	@Field("des_pic")
	private Picture desPic;
	
	@Field("ps_users")
	private List<PicPsUser> psUsers;
	
	public PicPsTask() {
		taskId = java.util.UUID.randomUUID().toString();
		taskStatus = TaskStatusEnum.UNDO.getIdx();
		psType = 0;
		param0 = "";
		userComment = "";
		workerComment = "";
		srcPics = new ArrayList<Picture>();
		desPic = new Picture();
		psUsers = new ArrayList<PicPsUser>();
	}

	public PicPsTask(JSONObject jsonObj) {
		taskId = java.util.UUID.randomUUID().toString();
		if (jsonObj == null || !jsonObj.has(JSON_KEY_PS_TYPE)) {
			psType = 0;
			param0 = "";
			userComment = "";
			workerComment = "";
			srcPics = new ArrayList<Picture>();
		} else {
			if (jsonObj.has(JSON_KEY_PS_TYPE)) {
				psType = jsonObj.getInt(JSON_KEY_PS_TYPE);
			}
			if (jsonObj.has(JSON_KEY_PARAM0)) {
				param0 = jsonObj.getString(JSON_KEY_PARAM0);
			}
			if (jsonObj.has(JSON_KEY_COMMENT0)) {
				userComment = jsonObj.getString(JSON_KEY_COMMENT0);
			}
			if (jsonObj.has(JSON_KEY_COMMENT1)) {
				workerComment = jsonObj.getString(JSON_KEY_COMMENT1);
			}
			if (jsonObj.has(JSON_KEY_PICS)) {
				srcPics = Picture.toPictures(jsonObj.getJSONArray(JSON_KEY_PICS));
			}
		}
	}
	
	public static List<PicPsTask> toTasks(JSONArray jsonArray){
		List<PicPsTask> tasks = new ArrayList<PicPsTask>();
		if (jsonArray != null) {
			for (int i=0; jsonArray!=null && i<jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				PicPsTask task = new PicPsTask(jsonObj);
				if (! task.getPsType().equals(0)) {
					tasks.add(task);
				}
			}
		}
		return tasks;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PicPsTask task = (PicPsTask) o;

		return !(taskId != null ? !taskId.equals(task.getTaskId()) : task.getTaskId() != null);
	}

	@Override
	public int hashCode() {
		return taskId != null ? taskId.hashCode() : 0;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public Integer getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	public List<Picture> getSrcPics() {
		return srcPics;
	}
	public void setSrcPics(List<Picture> srcPics) {
		this.srcPics = srcPics;
	}
	public Picture getDesPic() {
		return desPic;
	}
	public void setDesPic(Picture desPic) {
		this.desPic = desPic;
	}
	public List<PicPsUser> getPsUsers() {
		return psUsers;
	}
	public void setPsUsers(List<PicPsUser> psUsers) {
		this.psUsers = psUsers;
	}
	public Integer getPsType() {
		return psType;
	}
	public void setPsType(Integer psType) {
		this.psType = psType;
	}
	public String getParam0() {
		return param0;
	}
	public void setParam0(String param0) {
		this.param0 = param0;
	}
	public String getUserComment() {
		return userComment;
	}
	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}
	public String getWorkerComment() {
		return workerComment;
	}
	public void setWorkerComment(String workerComment) {
		this.workerComment = workerComment;
	}

}
