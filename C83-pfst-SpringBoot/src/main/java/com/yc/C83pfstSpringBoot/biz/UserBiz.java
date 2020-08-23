package com.yc.C83pfstSpringBoot.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yc.C83pfstSpringBoot.dao.UserDao;
import com.yc.damai.bean.DmUser;

@Service
public class UserBiz {
 
	@Resource
	private UserDao udao;
	 
	public DmUser login(String name,String pwd) throws BizException {
		 
			DmUser user=udao.selectByLogin(name, pwd);
		 if(user==null) {
			 throw new BizException("用户密码错误");
		 }
		 return user;
	}
}
