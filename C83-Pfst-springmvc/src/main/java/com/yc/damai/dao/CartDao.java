package com.yc.damai.dao;

import java.util.List;
import java.util.Map;

import com.yc.damai.util.DBHelper;

public class CartDao {
	//给某用户添加新的购物车商品
	// return 添加
     public int insert(String uid,String pid) {
    	  String sql="insert into dm_cart values(null,?,?,1)";
    	  return new DBHelper().update(sql, uid,pid);
     }
     /*
      * 给某个用户的购物车商品数量加1
      * 
      * return 更新的记录数
      */
     public int update(String uid,String pid) {
   	  String sql="update dm_cart set count=count+1 where uid=? and pid=?";
   	  return new DBHelper().update(sql, uid,pid);
    }
     
     public List<?> queryByUid(String uid){
     String sql = "select * from dm_cart a join dm_product b on a.pid=b.id where a.uid=?";
    	return new DBHelper().query(sql, uid); 
     }
     //购物车总价
     public  Map<String, Object> total(String uid){
         String sql = "SELECT\n" +
        		 " sum(b.shop_price*count) total \n" +
        		 "FROM\n" +
        		 "	dm_cart a\n" +
        		 "JOIN dm_product b ON a.pid = b.id\n" +
        		 "WHERE\n" +
        		 "	a.uid =? group by a.uid";
        	return new DBHelper().query(sql, uid).get(0); 
         }
     public void del(String id) {
      	  String sql="delete  from dm_cart where id=?";
      	 new DBHelper().update(sql, id);
       }
     
     public int deleteByUid(String uid) {
 		String sql = "delete from dm_cart where uid=?";
 		return new DBHelper().update(sql, uid);
 	}

     public void clear(String uid) {
     	  String sql="delete  from dm_cart where uid=?";
     	 new DBHelper().update(sql, uid);
      }

 
}
