package org.cloudfoundry.samples.fibonacci.web.controllers;

import java.util.Random;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.cloudfoundry.samples.fibonacci.domain.FibonacciRequest;
import org.cloudfoundry.samples.fibonacci.domain.FibonacciSession;
import org.slf4j.Logger;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(value = "/session")
public class FibonacciController {

	Logger logger = org.slf4j.LoggerFactory.getLogger(FibonacciController.class);
	
	@Autowired
	private RabbitTemplate template;
	
	Random random = new Random();
	
	@Autowired
	private RabbitAdmin admin;
	
	@Autowired
	private ObjectMapper mapper;
	
	@ResponseBody
	@RequestMapping(consumes="application/json", produces="application/json", method = RequestMethod.POST, value="/create")
	public FibonacciSession createSession(@RequestBody FibonacciSession sessionRequest){
		sessionRequest.setId(UUID.randomUUID().toString());
		logger.info("Creating session {}", sessionRequest);
		try {
			for(int i=0;i<sessionRequest.getCount();i++){
				FibonacciRequest request = new FibonacciRequest();
				request.setId(i);
				request.setSessionId(sessionRequest.getId());
				request.setValue(sessionRequest.getMin() + random.nextInt(sessionRequest.getMax()-sessionRequest.getMin()));
				template.convertAndSend("fibonacci", mapper.writeValueAsString(request));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sessionRequest;
	}
	
	
	@PostConstruct
	public void setup(){
		Queue queue = new Queue("fibonacci", false, false, true);
		admin.declareQueue(queue);
	}
}
