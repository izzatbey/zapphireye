package com.zapphireye.zapphireye;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.zaproxy.clientapi.core.ClientApiException;

@SpringBootApplication
public class ZapphireyeApplication {

	public static void main(String[] args) throws ClientApiException {
		SpringApplication.run(ZapphireyeApplication.class, args);
		new AutomateZap();
	}

}
