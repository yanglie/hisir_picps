package com.hisir.picps.controller.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hisir.picps.mongo.entity.PicPsUser;
import com.hisir.picps.mongo.entity.Picture;

@JsonInclude(Include.NON_NULL)
public class ProcTaskResultBizEntity {
	static final String JSON_KEY_TASK_ID  = "task_id";
	static final String JSON_KEY_TASK_STATUS  = "task_status";
	static final String JSON_KEY_USERS  = "workers";
	static final String JSON_KEY_PICTURE  = "picture";
	static final String JSON_KEY_COMMENT1  = "worker_comment";
	
	public String taskId;
	public Integer taskStatus;
	public List<PicPsUser> picPsUsers;
	public Picture picture;
	public String workerComment;
	
	public ProcTaskResultBizEntity(JSONObject jsonObj){
		if (jsonObj == null || !jsonObj.has(JSON_KEY_TASK_ID)) {
			taskId = "";
			taskStatus = 0;
			picPsUsers = new ArrayList<PicPsUser>();
			picture = new Picture();
			workerComment = "";
		} else {
			if (jsonObj.has(JSON_KEY_TASK_ID)) {
				taskId = jsonObj.getString(JSON_KEY_TASK_ID);
			}
			if (jsonObj.has(JSON_KEY_TASK_STATUS)) {
				taskStatus = jsonObj.getInt(JSON_KEY_TASK_STATUS);
			}
			if (jsonObj.has(JSON_KEY_TASK_ID)) {
				picPsUsers = PicPsUser.toPicPsUsers(jsonObj.getJSONArray(JSON_KEY_USERS));
			}
			if (jsonObj.has(JSON_KEY_PICTURE)) {
				picture = new Picture(jsonObj.getJSONObject(JSON_KEY_PICTURE));
			}
			if (jsonObj.has(JSON_KEY_COMMENT1)) {
				workerComment = jsonObj.getString(JSON_KEY_COMMENT1);
			}
		}
	}
	
	static public Map<String, ProcTaskResultBizEntity> toMapProcTaskResult(JSONArray jsonArray) {
		Map<String, ProcTaskResultBizEntity> mapProcTaskResult = new HashMap<String, ProcTaskResultBizEntity>();
		if (jsonArray != null) {
			for (int i=0; jsonArray!=null && i<jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				ProcTaskResultBizEntity procTaskResult = new ProcTaskResultBizEntity(jsonObj);
				mapProcTaskResult.put(procTaskResult.taskId, procTaskResult);
			}
		}
		return mapProcTaskResult;
	}
	
	
}
