package com.instar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Hello {
	
	@Bean
	public String hello2() {
		return "안녕";
	}

}
