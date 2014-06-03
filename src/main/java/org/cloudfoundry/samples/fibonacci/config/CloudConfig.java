package org.cloudfoundry.samples.fibonacci.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("cloud")
@Configuration
public class CloudConfig extends AbstractCloudConfig {

	@Bean
	public ConnectionFactory connection(){
		return connectionFactory().rabbitConnectionFactory();
	}
	
}
