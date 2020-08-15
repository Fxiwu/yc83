package com.yc.spring.bank.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.yc.spring.bank.bean.Oprecord;

/*
 * 流水
 */
@Repository
public class OprecordDao {
	/*
	 * 添加流水记录
	 * 
	 */
	@Autowired
	private JdbcTemplate jdbcTemplate;
       public int insert(int id,double money) {
   		return jdbcTemplate.update("insert into oprecord values(null,?,?,null,now())",id,money);
    	   
       }
       public List<Oprecord> selectById(int id){
	
    	   String sql="";
    	   Object[] args= {};
    	   
    	   return jdbcTemplate.query(sql,args,new RowMapper<Oprecord>() {
    		  
			 
			public Oprecord mapRow(ResultSet rs, int rowNum) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
    	   });
    	   
       }
       
}
