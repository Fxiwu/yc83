package com.yc.C83pfstblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yc.C83pfstblog.dao")
public class C83PfstBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(C83PfstBlogApplication.class, args);
	}

}
