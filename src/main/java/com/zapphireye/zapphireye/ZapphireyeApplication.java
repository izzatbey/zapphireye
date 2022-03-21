package com.zapphireye.zapphireye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
public class ZapphireyeApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZapphireyeApplication.class, args);
	}
}
