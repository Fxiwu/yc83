package web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import Dao.backOrderDao;
import po.DmAddress;
import po.DmOrders;
import po.DmProduct;
import util.DBHelper;
 
@WebServlet("/backorder.do")
public class BackOrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	backOrderDao bodao=new backOrderDao();
	protected void query1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {    
		String page=request.getParameter("page");
	    String rows=request.getParameter("rows");
       /**
 		 * dp  要装载的 实体 对象
 		 * properties 存放属性值的 map 集合
 		 */
 		DmOrders dp = new DmOrders();
 		// 装载方法
 		BeanUtils.populate(dp, request.getParameterMap());
 		List<?> list = bodao.query1(dp,page, rows);
 		int total = bodao.count1(dp);
 		System.out.println("list"+list);

 		System.out.println("total"+total);
        HashMap<String,Object> data=new HashMap<>();
        data.put("rows", list);
        data.put("total", total);
        print(response,data);
	}
	protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {    
		 String id=request.getParameter("id");
		 
		   try {
			   bodao.remove(id);
			    response.getWriter().append("删除成功");
			   
		} catch (IOException e) {
			 response.getWriter().append("删除失败");
		}
	}
	protected void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IllegalAccessException, InvocationTargetException {    
		 String id=request.getParameter("id");
		 String uid=request.getParameter("uid");
		 String name=request.getParameter("name");
 		 String total=request.getParameter("total");
		 String addr=request.getParameter("addr");
		 String state=request.getParameter("state");
		  int i=bodao.update(id, uid,name, total, addr, state);
		  if(i>0) {
			  		 response.getWriter().append("修改成功");

		  }
			   
		 
	}
	 	
}
