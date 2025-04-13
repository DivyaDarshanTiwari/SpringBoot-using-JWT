package com.example.springoot_using_jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.springoot_using_jwt.jwtauth.config.JwtConfig;

@SpringBootApplication
@EnableConfigurationProperties(JwtConfig.class)
public class SpringootUsingJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringootUsingJwtApplication.class, args);
	}

}
