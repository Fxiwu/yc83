package Dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

import bean.DmCategory;
import bean.DmOrderitem;
import bean.DmOrders;
import bean.DmProduct;

public interface DmOrdersMapper {

	List<DmOrders> selectAll();
	DmOrders selectById(int id);
	//新增订单
	@Insert("insert into dm_orders values(null,#{total},now(),#{state},#{uid},#{aid})")
 	@Options(useGeneratedKeys= true ,keyProperty="id" ,keyColumn="id")
    int insert(DmOrders dos);
}
