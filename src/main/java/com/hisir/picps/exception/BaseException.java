package com.hisir.picps.exception;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import com.hisir.picps.enums.ErrCodeEnum;


public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String message, String cause) {
		super(message, new Throwable(cause));
	}
	
	public BaseException(ErrCodeEnum errCode) {
		super(String.valueOf(errCode.getCode()), new Throwable(errCode.getMsg()));
	}
	
	public static String getAllExInfo(Exception ex) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pout = new PrintStream(out);
        ex.printStackTrace(pout);
        String ret = new String(out.toByteArray());
        pout.close();
        try {
             out.close();
        } catch (Exception e) {
        }
        return ret;
	 }
}
