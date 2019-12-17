package com.smile.demo.springbootmybatisdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.smile.demo.springbootmybatisdemo.mapper")
public class SpringBootMybatisDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisDemoApplication.class, args);
	}

}
