package com.event;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = "com.event")
@SpringBootApplication
public class QuanLySuKienApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuanLySuKienApplication.class, args);
	}

}
