package com.cuc.gin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cuc.gin.mapper")
public class GinApplication {

	public static void main(String[] args) {
		SpringApplication.run(GinApplication.class, args);
	}

}
