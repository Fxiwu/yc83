package web;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
 

import Dao.DmCategoryMapper;
import Dao.DmProductMapper;
import Dao.ProductDao;
import bean.DmCategory;
import po.DmProduct;
import po.Result;
import util.DBHelper;
 
@WebServlet("/product.do")
public class ProductServlet extends BaseServlet{
	private static final long serialVersionUID = 1L;
	private ProductDao pdao = new ProductDao();
	 
	 private SqlSession session;
	protected void query1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {    
		String page=request.getParameter("page");
	    String rows=request.getParameter("rows");
       /**
 		 * dp  要装载的 实体 对象
 		 * properties 存放属性值的 map 集合
 		 */
 		DmProduct dp = new DmProduct();
 		// 装载方法
 		BeanUtils.populate(dp, request.getParameterMap());
 		List<?> list = pdao.query1(dp,page, rows);
 		int total = pdao.count1(dp);
       HashMap<String,Object> data=new HashMap<>();
       data.put("rows", list);
       data.put("total", total);
       print(response,data);
	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String sql="select * from dm_product where is_hot=1 order by id desc limit 0,10";
         List<?> list=new DBHelper().query(sql);
         HashMap<String,Object> page=new HashMap();
         page.put("list",list);
         print(response,page);
	}
	//查询某件商品
	protected void detail(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		String cid=request.getParameter("id");
		String sql="select * from dm_product where id=?";
		List<?> list = new DBHelper().query(sql, cid);
        print( response, list.get(0));
	}
     //最新商品
	protected void least(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql="select * from dm_product order by createtime desc limit 0,10";
//           print(response, new DBHelper().query(sql));
//           System.out.println("*******"+ new DBHelper().query(sql));
        List<?> newlist=new DBHelper().query(sql);
        HashMap<String,Object> page1=new HashMap();
        page1.put("newlist",newlist);
        print(response,page1);
	}
	 
	//商品分类查询
	protected void categroy(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         String cid=request.getParameter("cid");
         String csid=request.getParameter("csid");
         System.out.println("cid:"+cid);
         System.out.println("csid:"+cid);
//		String sql="select * from dm_product where cid=? limit 0,10";
//       List<?> list=new DBHelper().query(sql,cid);
     
      
			 
				try {
					// mybatis 配置文件
					String resource = "mybatis.xml";
					// 读入配置文件
					InputStream inputStream = Resources.getResourceAsStream(resource);
					// 构建会话工厂  ==>  23 设计模式   工厂模式
					SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					 session = sqlSessionFactory.openSession();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//         
//         DmCategoryMapper mapper=session.getMapper(DmCategoryMapper.class);
// 	     List< DmCategory> dcList=mapper.selectAll();
 	     HashMap<String,Object> page=new HashMap();
 	    
 	    DmProductMapper mapper=session.getMapper(DmProductMapper.class);
 	   int  cids=Integer.parseInt(cid);

  	    System.out.println(   mapper.selectByCids(cids));
      page.put("list", mapper.selectByCids(cids));
      print(response,page);
   
	}
	
	protected void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
 		DmProduct dp = new DmProduct();
 		// 装载方法
 		BeanUtils.populate(dp, request.getParameterMap());
       	// 商品名称验证 空值验证, 长度判断
 		if(dp.getPname()==null || dp.getPname().trim().isEmpty()) {
 		print( response, new Result(0,"商品名称不能为空!"));
 			return;
 		}
 		if(dp.getShopPrice()==null || dp.getShopPrice()<=0) {
 			print( response, new Result(0,"商品商城价格必须大于0!"));
 			return;
 		}
 	// id 为空或者等于0 是新增
 			if(dp.getId() == null || dp.getId() == 0) {
 			pdao.insert(dp);
 			print( response, new Result(1,"商品添加成功!"));
 		}else {
 			pdao.update(dp);
 			print( response, new Result(1,"商品修改成功!"));

 		}
 		
 		
	}
	//删除
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {
		 String id=request.getParameter("id");
		 
		   try {
			   pdao.remove(id);
			    response.getWriter().append("删除成功");
			   
		} catch (IOException e) {
			 response.getWriter().append("删除失败");
		}
	}
}
