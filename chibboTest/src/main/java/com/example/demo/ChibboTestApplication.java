package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//전체를 스캔
@MapperScan(basePackages = "com.example.demo")
@SpringBootApplication
public class ChibboTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChibboTestApplication.class, args);
	}

}
 