package com.hisir.picps.mongo.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Document
public class PicPsPriority implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	static public int STATUS_INVALID = 0;
	static public int STATUS_VALID = 1;
		
	@Id
	private String id;
	
	@Field("bitint")
	private Integer bitInt;
	
	@Field("name")
	private String name;
	
	@Field("priority")
	private Integer priority;
	
	@Field("status")
	private Integer status;
	
	@Field("modify_time")
	private Long modifyTime;
		
	
	public PicPsPriority() {
		bitInt = 0;
		name = "";
		priority = 500;
		status = 0;
		modifyTime = (long) 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getBitInt() {
		return bitInt;
	}

	public void setBitInt(Integer bitInt) {
		this.bitInt = bitInt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Long modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}
