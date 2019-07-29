package com.hisir.picps.enums;

public enum PayStatusEnum {

	EDIT(0),WAIT(1),SUCC(2),FAIL(3),CANCEL(4),DELETE(5);
	
	private PayStatusEnum(int index) {
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
