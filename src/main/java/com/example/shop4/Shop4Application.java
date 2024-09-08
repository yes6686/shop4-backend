package com.example.shop4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // @CreatedDate와 @LastModifiedDate 활성화
public class Shop4Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Shop4Application.class, args);
	}
}