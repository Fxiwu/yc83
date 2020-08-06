package Dao;

import java.util.List;

import bean.DmCategory;
import bean.DmOrderitem;
import bean.DmProduct;

public interface DmOrderitemMapper {

	List<DmOrderitem> selectAll();
	DmOrderitem selectById(int id);
	//新增订单明细
	int insert(DmOrderitem doi);

}
