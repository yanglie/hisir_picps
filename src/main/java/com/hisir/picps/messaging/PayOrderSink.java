package com.hisir.picps.messaging;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface PayOrderSink {
	static String INPUT = "payorderinput";
	
	@Input(PayOrderSink.INPUT)
	SubscribableChannel input();
}
