package com.hisir.picps.mongo.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;

public class PicPsWatermark implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	@Indexed
	@Field("uid")
	private Integer uid;
	
	@Field("texts")
	private Map<String, String> waterTxt;
	
	@Field("pics")
	private List<PicPsWaterPicInfo> waterPic;
	
	@Field("modify_time")
	private Long modifyTime;
	
	public PicPsWatermark(Integer uid) {
		this.uid = uid;
		this.waterTxt = new TreeMap<String, String>();
		this.waterPic = new ArrayList<PicPsWaterPicInfo>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Map<String, String> getWaterTxt() {
		return waterTxt;
	}

	public void setWaterTxt(Map<String, String> waterTxt) {
		this.waterTxt = waterTxt;
	}

	public List<PicPsWaterPicInfo> getWaterPic() {
		return waterPic;
	}

	public void setWaterPic(List<PicPsWaterPicInfo> waterPic) {
		this.waterPic = waterPic;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
}
