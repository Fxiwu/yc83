package com.yc.user.web;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="order",fallback =OrderActionHytrix.class)
public interface IOrderAction {

	@GetMapping("order")
	public String order();
}
