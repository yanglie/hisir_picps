package com.hisir.picps.mongo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.mongodb.core.mapping.Field;

public class Picture implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static final String JSON_KEY_PIC_ID  = "picture_id";
	static final String JSON_KEY_WIDTH  = "width";
	static final String JSON_KEY_HEIGHT = "height";
	
	//原图
	@Field("pic_id")
	private String picId;
	@Field("width")
	private Integer width;
	@Field("height")
	private Integer height;
	
	public Picture() {
		picId = "";
		width = 0;
		height = 0;
	}
	
	public Picture(JSONObject jsonObj){
		if (jsonObj == null || !jsonObj.has(JSON_KEY_PIC_ID)) {
			picId = "";
			width = 0;
			height = 0;
			return;
		}
		if (jsonObj.has(JSON_KEY_PIC_ID)) {
			picId = jsonObj.getString(JSON_KEY_PIC_ID);
		}
		if (jsonObj.has(JSON_KEY_WIDTH)) {
			width = jsonObj.getInt(JSON_KEY_WIDTH);
		}
		if (jsonObj.has(JSON_KEY_HEIGHT)) {
			height = jsonObj.getInt(JSON_KEY_HEIGHT);
		}
		return;
	}
	
	public static List<Picture> toPictures(JSONArray jsonArray){
		List<Picture> pictures = new ArrayList<Picture>();
		if (jsonArray != null) {
			for (int i=0; jsonArray!=null && i<jsonArray.length(); i++) {
				JSONObject jsonObj = jsonArray.getJSONObject(i);
				Picture pic = new Picture(jsonObj);
				if (! pic.getPicId().equals("")) {
					pictures.add(pic);
				}
			}
		}
		return pictures;
	}
	
	

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	
	
//	@Field("pic_id")
//	private String picId;
//	
//	@Field("pic_spec")
//	private String picSpec; //图片规格：widthxheight
//
//	public PicPsInfo() {
//		picId = "";
//		picSpec = "";
//	}
//	
//	public PicPsInfo(String picId, String picSpec) {
//		this.picId = (picId == null) ? "": picId;
//		this.picSpec = (picSpec == null) ? "": picSpec;
//	}


	
}
