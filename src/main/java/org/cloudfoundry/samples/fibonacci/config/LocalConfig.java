package org.cloudfoundry.samples.fibonacci.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("local")
public class LocalConfig {

	@Bean
	public ConnectionFactory connection() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
		return connectionFactory;
	}
	
	
}
