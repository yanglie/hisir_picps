package com.hisir.picps.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hisir.picps.enums.ErrCodeEnum;

@JsonInclude(Include.NON_NULL)
public class BaseResponse<T> {
	public int error;
	public String message;
	private T data;
	
	public BaseResponse() {
	}
	public BaseResponse(ErrCodeEnum errCodeEnum) {
		this.error = errCodeEnum.getCode();
		this.message = errCodeEnum.getMsg();
		this.data = null;
	}
	public BaseResponse(ErrCodeEnum errCodeEnum, T data) {
		this.error = errCodeEnum.getCode();
		this.message = errCodeEnum.getMsg();
		this.data = data;
	}

	public BaseResponse(int code, String msg) {
		this.error = code;
		this.message = msg;
		this.data = null;
	}

	public BaseResponse(int code, String msg, T data) {
		this.error = code;
		this.message = msg;
		this.data = data;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
