package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


//전체를 스캔
@SpringBootApplication
@MapperScan(basePackages = "com.example.demo")
public class TestProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestProjectApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	      return builder.sources(TestProjectApplication.class);
	}
	
	

}
