package com.yc.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan("com.yc.spring")
/*
 * 
 */
@EnableAspectJAutoProxy
public class AOPConfig {
  
}
