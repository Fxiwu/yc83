package com.yc.damai.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.yc.damai.bean.DmCategory;
import com.yc.damai.bean.DmOrderitem;
import com.yc.damai.bean.DmOrders;
import com.yc.damai.bean.DmProduct;
@RunWith(SpringRunner.class)
@ContextConfiguration("/beans.xml")
public class MapperTest {
	 @Resource
	 DmProductMapper mapper;
	@Test
	public void Test13() throws IOException {
		//DmProductMapper mapper = session.getMapper(DmProductMapper.class);
		mapper.selectById(1);

	}

}
