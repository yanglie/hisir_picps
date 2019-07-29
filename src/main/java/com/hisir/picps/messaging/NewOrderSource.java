package com.hisir.picps.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NewOrderSource {
	static String OUTPUT = "neworderoutput";
	
	@Output(NewOrderSource.OUTPUT)
	MessageChannel newOrderOutput();
}
