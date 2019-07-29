package com.hisir.picps.mongo.entity;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Field;

public class PicPsWaterPicInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Field("pic_uuid")
	private String picUuid;
	
	@Field("pic_name")
	private String picName;
	
	@Field("comment")
	private String comment;
	
	@Field("pic_info")
	private Picture picInfo;
	
	public PicPsWaterPicInfo() {
		picUuid = java.util.UUID.randomUUID().toString();
		picName = "";
		comment = "";
		picInfo = null;
	}

	public PicPsWaterPicInfo(String picName, String comment, Picture picInfo) {
		picUuid = java.util.UUID.randomUUID().toString();
		this.picName = picName;
		this.comment = comment;
		this.picInfo = picInfo;
	}
	
	public String getPicUuid() {
		return picUuid;
	}

	public void setPicUuid(String picUuid) {
		this.picUuid = picUuid;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Picture getPicInfo() {
		return picInfo;
	}

	public void setPicInfo(Picture picInfo) {
		this.picInfo = picInfo;
	}
	
}
