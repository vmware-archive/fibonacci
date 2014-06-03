package org.cloudfoundry.samples.fibonacci.config;

import javax.annotation.PostConstruct;

import org.cloudfoundry.samples.fibonacci.messaging.FibonacciMessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude={RabbitAutoConfiguration.class})
@ComponentScan(basePackages = { "org.cloudfoundry.samples.fibonacci" })
public class AppConfig {

	@Bean
	@Autowired
	public RabbitAdmin admin(ConnectionFactory connection) {
		return new RabbitAdmin(connection);
	}

	@Bean
	@Autowired
	public RabbitTemplate template(ConnectionFactory cf) {
		return new RabbitTemplate(cf);
	}
	
	@Bean
	public SimpleMessageListenerContainer container(ConnectionFactory connection, FibonacciMessageListener listener) throws Exception{
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connection);
		container.setQueueNames("fibonacci");
		container.setMessageListener(new MessageListenerAdapter(listener, "onMessage"));
		return container;
	}

	
}
