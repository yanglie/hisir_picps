package com.hisir.picps.enums;

public enum PsStatusEnum {
	//0-未处理
	//1-处理中
	//2-处理完成
	//3-用户接受
	//4-用户拒绝
	//5-没有图片（未上传）
	UNDO(0),DOING(1),DONE(2),ACCEPT(3),REJECT(4),NOPIC(5);
	
	private PsStatusEnum(int index) {
		this.idx = index;  
	}
	private int idx;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	} 
	

}
