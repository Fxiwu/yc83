package com.yc.C83pfstblog.biz;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yc.C83pfstblog.bean.User;
import com.yc.C83pfstblog.dao.UserMapper;

@Service
public class UserBiz {

	@Resource
	private UserMapper uMapper;
	
	public void regist(User user) throws BizException {
		//同名验证
		if(uMapper.countByAcount(user.getAccount())>0) {
			throw new BizException("用户已经存在");
			
		}
		uMapper.insert(user);
	}
	
	public User login(User user) throws BizException {
		 
		User dbuser=uMapper.selectByLogin(user);
		if(dbuser==null) {
			throw new BizException("用户名或密码错误");
		}

        return dbuser;
	}
	
}
