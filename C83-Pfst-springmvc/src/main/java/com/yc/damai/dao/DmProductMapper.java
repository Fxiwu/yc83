package com.yc.damai.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.yc.damai.bean.DmCategory;
import com.yc.damai.bean.DmProduct;

public interface DmProductMapper {
/*
 * select注解会默认使用  resultType的映射行为
 */
	@Select("select * from dm_product")
	@Results(id="rmdp",value= {
			@Result(column="id", property="id",id=true),
			@Result(column="market_price", property="marketPrice"),
			@Result(column="shop_price", property="shopPrice"),
			@Result(column="is_hot", property="isHot") })
 	 
	List<DmProduct> selectAll();
	@Select("select * from dm_product where id=#{id}")
	//@ResultMap代替<select>标签的resultMap属性
	@ResultMap("rmdp")
	DmProduct selectById(int id);
	
	@Select("select * from dm_product where is_hot=1")
    List<DmProduct> selectForHot();
 
	
	List<DmProduct> selectByObj(DmProduct dp);
	/*
	 * 根据枚举的分类id进行查询
	 * @param cids 存放分类id数组 0~N
	 * mybatis  不能识别参数的名称
	 * @param 用于定义参数的名称
	 */
	
	// List<DmProduct> selectByCids(@Param("cids") int[] cids);
	List<DmProduct> selectByCids( int cids);
     int update(DmProduct dp);
     
     int insert(DmProduct dc);
     int delete(int dc);
      
}
