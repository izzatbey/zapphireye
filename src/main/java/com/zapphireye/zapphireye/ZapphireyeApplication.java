package com.zapphireye.zapphireye;

import com.zapphireye.zapphireye.helper.AutomateZap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.zaproxy.clientapi.core.ClientApiException;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ZapphireyeApplication {

	public static void main(String[] args) throws ClientApiException {
		SpringApplication.run(ZapphireyeApplication.class, args);
	}

}
