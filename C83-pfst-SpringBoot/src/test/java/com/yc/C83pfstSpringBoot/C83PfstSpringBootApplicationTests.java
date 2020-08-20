package com.yc.C83pfstSpringBoot;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.yc.C83pfstSpringBoot.biz.MailService;
import com.yc.C83pfstSpringBoot.dao.ProductMapper;

@SpringBootTest
class C83PfstSpringBootApplicationTests {

	@Resource
	ProductMapper pm;
	
	@Resource
	MailService ms;
	
	@Test
	void contextLoads() {
		Assert.isTrue(pm.selectAll().size()>0,"没有数据");
	}
	
	@Test
	void testMail() {
		ms.sendSimpleMail("1363121722@qq.com","密码重置验证码","您的密码重置验证码为123445");
	}

}
