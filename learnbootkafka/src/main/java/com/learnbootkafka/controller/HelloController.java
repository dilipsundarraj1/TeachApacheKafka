package com.learnbootkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@Autowired
	Environment env;
	
	
	@RequestMapping(value="/home")
	public String getResult(){
		
		//return "Hello from Spring Boot"; 
		return env.getProperty("message.response");
	}

}
