package com.hisir.picps.enums;

public enum PayTypeEnum {

//	public static final int ORDER_TYPE_0 = 0;  //购买套餐
//	public static final int ORDER_TYPE_1 = 1;  //离线翻译支付，展览会
//	public static final int ORDER_TYPE_2 = 2;  //离线翻译支付，产品翻译
//	public static final int ORDER_TYPE_3 = 3;  //图片处理
	
	TYPE0(0),TYPE1(1),TYPE2(2),TYPE3(3);
	
	private PayTypeEnum(int index) {
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
