package com.yc.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 
@EnableEurekaClient
@SpringBootApplication
//开启声明式服务调用
@EnableFeignClients
//服务降级注解
@EnableCircuitBreaker
public class UserApplication implements WebMvcConfigurer{
	 

		public static void main(String[] args) {
			SpringApplication.run(UserApplication.class, args);
		}
		
		
		@LoadBalanced
		@Bean //定义RestTemplate Bean
		public RestTemplate getRestTemplate() {
			return new RestTemplate();
		}
}
