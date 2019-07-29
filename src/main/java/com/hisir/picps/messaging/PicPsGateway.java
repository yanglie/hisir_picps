package com.hisir.picps.messaging;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface PicPsGateway {

	@Gateway(requestChannel="neworderoutput")
	public void sendNewOrderNo(String orderNo);
}
