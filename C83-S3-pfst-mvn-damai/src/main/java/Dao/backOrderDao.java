package Dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import po.DmAddress;
import po.DmOrders;
import po.DmProduct;
import util.DBHelper;

public class backOrderDao {
	//向backorder表中插入数据
	
	public int insert() {
		 String sql= "INSERT  INTO  backorder(\n" +
				 "	id,\n" +
				 "  total,\n" +
				 "  createtime,\n" +
				 "   state,\n" +
				 "   uid,\n" +
				 "   aid,\n" +
				 "   addr,\n" +
				 "   name\n" +
				 ")SELECT\n" +
				 "	a.id,total,a.createtime,state,a.uid,aid ,addr,name\n" +
				 "FROM\n" +
				 "	dm_orders a\n" +
				 "JOIN dm_address b ON aid = b.id\n" +
				 "where a.id not in(\n" +
				 "  select id from backorder\n" +
				 ")";
		 return new DBHelper().update(sql);
	}
	public List<Map<String,Object>> query1(DmOrders dp, String page, String rows){
		insert();
		String where = "";
	    List<Object> params=new ArrayList<>();
	    if(dp.getId()!=null ) {
 			where += " and id = ?";
 			params.add(dp.getId());
 		}
	    if(dp.getUid()!= null) {
 			where += "  and uid = ?";
 			params.add(dp.getUid());
 		}

 	if(dp.getState()!= null) {
 		where += "  and state = ?";
 			params.add(dp.getState());
 		}
	   int ipage=Integer.parseInt(page);
	   int irows=Integer.parseInt(rows);
	 
	   ipage=(ipage-1)*10;
	   String sql ="SELECT\n" +
			   "	*\n" +
			   "FROM\n" +
			   "	backorder\n" +
			   "WHERE\n" +
			   "	1 = 1"
	   	  	+ where
 				+ " limit ?,?";
	   params.add(ipage);
 		params.add(irows);
 		return new DBHelper().query(sql, params.toArray());
    }
	public int count1(DmOrders dp) {
		insert();
		   String where = "";
	 		List<Object> params = new ArrayList<>();
	 		 if(dp.getId()!=null ) {
	  			where += " and id = ?";
	  			params.add( dp.getId() );
	  		}
	 	    if(dp.getUid() != null) {
	  			where += "  and uid = ?";
	  			params.add(dp.getUid());
	  		}

	  	if(dp.getState()!= null) {
	  		where += "  and state = ?";
	  			params.add(dp.getState());
	  		}
	 	 
	 		String sql = "select * from backorder where 1=1" + where;
	 		return new DBHelper().count(sql, params.toArray());
	    }
	public int remove(String id) {
		 String sql1="delete from dm_orders where id=?";
		 String sql2="delete from dm_orderitem where oid=?";
		 String sql="delete from backorder where id=?";
		 new DBHelper().update(sql2, id);

		 new DBHelper().update(sql1, id);
		 return new DBHelper().update(sql, id);
	}
	public int update(String id,String uid,String name,String total,String addr,String state) {
		 String sql="UPDATE backorder\n"+
				 " SET\n" +
				 "	uid=? ,\n" +
				 "	name=? ,\n" +
				 "	total=? ,\n" +
				 "	addr=? ,\n" +
				 "	state=?\n" +
				 " \n" +
				 "where id=?";
		 String sql1="update dm_orders "
		 		+ "set "
		 		+ "uid=? ,"
		 		+ "total=?,"
		 		+ "state=?"
		 		+ "where id=?";
		 new DBHelper().update(sql1, uid,total,state,id);
		 return new DBHelper().update(sql, uid,name,total, addr, state,id);
	}
}
