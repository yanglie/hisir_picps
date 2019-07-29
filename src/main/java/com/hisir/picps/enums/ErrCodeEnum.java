package com.hisir.picps.enums;

public enum ErrCodeEnum {
	
	SYS_ERR_SPRING_CLOUD(-3, "service unavailable"),  //服务不可用
	SYS_ERR_DB_OPERATION(-2, "db operate err"),  //数据库错误
	SYS_ERR_UNKNOWN(-1, "unknown system error"),  //未知错误
	SUCCESS(0, "success"),  //成功
	//PicPsUnsatisfiedTaskController
	M01_INVALID_ORDER_NO(10160101, "invalid buy order id"),    // 无效orderNo
	//PicPsProcStatusController
	M02_INVALID_ORDER_NO(10160201, "invalid buy order id"),    // 无效orderNo
	//PicPsFinishOrderController
	M03_INVALID_ORDER_NO(10160301, "invalid buy order id"),    // 无效orderNo
	//PicPsQueryOrderController
	M04_INVALID_INPUT_PARAM(10160401, "input param err"),  //无效输入参数
	//PicPsUndesirableTaskController
	M05_INVALID_ORDER_NO(10160501, "invalid buy order id"),    // 无效orderNo
	;
	private final int code;
	private final String msg;
	
	private ErrCodeEnum(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public String toString() {
		return name();
	}
	
	public static ErrCodeEnum getByMsg(String msg) {
		try {
			return valueOf(msg.toUpperCase());
		}
		catch (Exception e) {
			return SYS_ERR_UNKNOWN;
		}
	}
	
	public static ErrCodeEnum getByCode(int code) {
		try {
			for (ErrCodeEnum at : ErrCodeEnum.values()) {
				if(at.code == code)
					return at;
			}
		}
		catch (Exception e) {
			return SYS_ERR_UNKNOWN;
		}
		return null;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}
