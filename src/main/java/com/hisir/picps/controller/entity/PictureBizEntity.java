package com.hisir.picps.controller.entity;

import java.util.ArrayList;
import java.util.List;

import com.hisir.picps.mongo.entity.Picture;

public class PictureBizEntity {
	public String picture_id;
	public Integer width;
	public Integer height;
	
	PictureBizEntity() {
		picture_id = "";
		width = 0;
		height = 0;
	}
	public PictureBizEntity(Picture picPsInfo) {
		if (picPsInfo != null) {
			picture_id = picPsInfo.getPicId();
			width = picPsInfo.getWidth();
			height = picPsInfo.getHeight();
		} else {
			picture_id = "";
			width = 0;
			height = 0;
		}
	}
	
	static public List<PictureBizEntity> toPics(List<Picture> pics) {
		List<PictureBizEntity> picsBiz = new ArrayList<PictureBizEntity>();
		if (pics != null) {
			for (Picture pic: pics) {
				picsBiz.add(new PictureBizEntity(pic));
			}
		}
		return picsBiz;
	}
}
