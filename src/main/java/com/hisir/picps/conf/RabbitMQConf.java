package com.hisir.picps.conf;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 作用: 连接amqp  rabbitmq
 * @author yanglie
 * date: 2019-7-18
 *
 */
@Configuration
public class RabbitMQConf {
	/**
	 * 引用文件
	 * application.yml
	 */
	@Value("${rabbitmq.host}")
	private String host;
	@Value("${rabbitmq.port}")
	private String port;
	@Value("${rabbitmq.username}")
	private String username;
	@Value("${rabbitmq.password}")
	private String password;
	@Value("${rabbitmq.virtual-host}")
	private String virtualhost;
	@Value("${rabbitmq.queuename}")
	private String queuename;
	
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(host+":"+port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualhost);
        connectionFactory.setPublisherConfirms(true);//消息确认
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    //注意此下两个bean 有用  有坑时用到
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }

}