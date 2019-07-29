package com.hisir.picps.mongo.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document
public class PicPsLog implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	@Indexed
	@Field("uid")
	private Integer uid;

	@Indexed
	@Field("order_no")
	private String orderNo;
		
	@Field("pic_type")  //0-单图处理，1-多图合成
	private Integer picType;
	
	@Field("ps_type")
	private Integer psType;
	
	@Field("pic_ids_src")
	private List<String> pic_ids_src;
	
	@Field("pic_id_des")
	private String pic_id_des;
	
	@Field("proc_user")
	private String procUser;
	
	@Field("create_time")
	private Long createTime;
	
	
	public PicPsLog() {
		picType = 0;
		psType = 0;
	}
	

	
//	@Override
//	  public String toString() {
//	    return "Feedback [id=" + id + ", uid=" + uid + ", type=" + type + ", msg=" + msg 
//	    		+ ", createTime=" + createTime + "]";
//	  }
	
	
}
