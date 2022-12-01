package com.springactivemq.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Session;

@Configuration
@ComponentScan
@EnableJms
public class JMSConfig {
	@Bean
	public ActiveMQConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL("tcp://localhost:61616");
		factory.setTrustAllPackages(true);
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
		jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		return jmsTemplate;
	}

//	@Bean
//	public JmsListenerContainerFactory jmsListenerContainerFactory() {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory());
//		//core poll size=4 threads and max poll size 8 threads
//		factory.setConcurrency("1-1");
//		return factory;
//	}
	@Bean(name = "jmsListenerContainerFactory")
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
			@Value("${spring.jms.listener.auto-startup}") boolean autoStart) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		factory.setSessionTransacted(true);
		factory.setConnectionFactory(connectionFactory());
    	factory.setAutoStartup(autoStart);
		return factory;
	}

}
