package com.secauth.spring.secauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class SecauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecauthApplication.class, args);
	}

}
