package com.hisir.picps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hisir.picps.messaging.NewOrderSource;
import com.hisir.picps.messaging.PayOrderSink;

@EnableDiscoveryClient
@EnableFeignClients
@EnableMongoRepositories(basePackages = "com.hisir.picps.mongo.service")
@EnableTransactionManagement
@EnableBinding({NewOrderSource.class, PayOrderSink.class})
@IntegrationComponentScan
@SpringBootApplication
public class BootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
