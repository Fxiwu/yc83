package com.yc.C83pfstSpringBoot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@SpringBootApplication
@MapperScan("com.yc.C83pfstSpringBoot.dao")
//开启定时任务
@EnableScheduling
public class C83PfstSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(C83PfstSpringBootApplication.class, args);
	}
	
	@Bean
	public ServerEndpointExporter ServerEndpointExporter() {
		return new ServerEndpointExporter();
	}

}
