package org.cloudfoundry.samples.fibonacci.messaging;

import java.io.IOException;

import org.cloudfoundry.samples.fibonacci.Fibonacci;
import org.cloudfoundry.samples.fibonacci.domain.FibonacciRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FibonacciMessageListener {
	
	@Autowired
	private ObjectMapper mapper;

	private Logger logger = LoggerFactory.getLogger(FibonacciMessageListener.class);
	
	public void onMessage(String message){
		logger.debug("Starting fibonacci compute for :  {}", message);
		FibonacciRequest request;
		try {
			request = mapper.readValue(message, FibonacciRequest.class);
			Long start = System.currentTimeMillis();
			Fibonacci.fib(request.getValue());
			Long end = System.currentTimeMillis();
			logger.info("Computed sequence for {} in {} ms",request,(end-start));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
