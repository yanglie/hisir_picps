package com.hisir.picps.mongo.entity;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PicPsUser {
	
	static final String JSON_KEY_PIC_TYPE  = "ps_type";
	static final String JSON_KEY_USER  = "worker";
	
	public Integer psType;
	public String user;
	
	public PicPsUser() {
		psType = 0;
		user = "";
	}
	public PicPsUser(JSONObject jsonObj){
		if (jsonObj == null || !jsonObj.has(JSON_KEY_PIC_TYPE) || !jsonObj.has(JSON_KEY_USER)) {
			user = "";
			psType = 0;
			return;
		}
		if (jsonObj.has(JSON_KEY_PIC_TYPE)) {
			psType = jsonObj.getInt(JSON_KEY_PIC_TYPE);
		}
		if (jsonObj.has(JSON_KEY_USER)) {
			user = jsonObj.getString(JSON_KEY_USER);
		}
		return;
	}
	
	public static List<PicPsUser> toPicPsUsers(JSONArray jsonArray){
		List<PicPsUser> procUsers = new ArrayList<PicPsUser>();
		if (jsonArray != null) {
			for (int i=0; jsonArray!=null && i<jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				procUsers.add(new PicPsUser(jsonObj));
			}
		}
		return procUsers;
	}
}
