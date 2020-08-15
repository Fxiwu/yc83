package com.yc.spring.bank.biz;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.spring.bank.dao.AccountDao;
import com.yc.spring.bank.dao.OprecordDao;

/*
 * 银行业务类
 * 开户：向Account表中添加一天记录
 * 存取款：修改Account的余额，记录流水表
 *       卡号 密码  余额
 *转账：
 *查询：
 */
@Service
@Transactional(rollbackFor = {IOException.class,SQLException.class})
public class BankBiz {
	@Autowired
	private AccountDao adao;
	@Autowired
	private OprecordDao odao;
	//开户
	public void register(int id,String pwd,double money) {
		/*
		 * jdbcTemplate 每个update都有独立的事务控制
		 * try{
		 * 业务代码=》回调
		 * 提交操作
		 * }catch(){
		 *    回滚操作
		 * }finally{
		 * 
		 * }
		 */
		//省略参数校验
		adao.insert(id, pwd, money);
		odao.insert(id,money);

	}
	//存取款
	@Transactional(rollbackFor = {BizException.class})
	public void save(int id,double money) throws BizException {
		//省略参数校验
		adao.update(id, money);
		if(money>999) {
			throw new BizException("存取金额不能大于999");
		}
		odao.insert(id,money);
	}
	//
	public void transfer(int id1, int id2,double money) {
		adao.update(id1, -money);
		adao.update(id2, money);
	}
	
	
	
	
	
	
	
}
