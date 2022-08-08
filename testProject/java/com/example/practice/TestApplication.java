package com.example.practice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@MapperScan(basePackages = "com.example.practice")
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(TestApplication.class);
	}
	

}
