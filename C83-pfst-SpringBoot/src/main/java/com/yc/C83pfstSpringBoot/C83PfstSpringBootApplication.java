package com.yc.C83pfstSpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yc.C83pfstSpringBoot.dao")
public class C83PfstSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(C83PfstSpringBootApplication.class, args);
	}

}
