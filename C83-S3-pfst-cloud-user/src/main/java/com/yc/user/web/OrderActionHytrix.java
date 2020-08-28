package com.yc.user.web;

import org.springframework.stereotype.Component;

@Component
public class OrderActionHytrix implements IOrderAction {
 

	@Override
	public String order() {
		 
		return "声明式的order服务接口降级回复信息";
	}

}
