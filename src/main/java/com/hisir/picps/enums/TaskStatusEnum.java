package com.hisir.picps.enums;

public enum TaskStatusEnum {
	//0-未处理
	//1-处理中
	//2-已处理
	//3-图片不合格undesirable
	//4-用户不满意unsatisfied
	UNDO(0),DOING(1),DONE(2),UNDESIRABLE(3),UNSATISFIED(4);
	
	private TaskStatusEnum(int index) {
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
