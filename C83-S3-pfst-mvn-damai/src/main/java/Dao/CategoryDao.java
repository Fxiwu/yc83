package Dao;

import java.util.List;
import java.util.Map;

import util.DBHelper;

public class CategoryDao {
	   
     public List<?> query ( ){
     	return new DBHelper().query("select * from dm_category"); 
     }
   
}
