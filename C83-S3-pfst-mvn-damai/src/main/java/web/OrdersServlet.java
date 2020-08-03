package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Dao.CartDao;
import Dao.OrderitemDao;
import Dao.OrdersDao;
 
@WebServlet("/orders.do")
public class OrdersServlet extends  BaseServlet {
	private static final long serialVersionUID = 1L;
	private OrdersDao odao = new OrdersDao();
     private OrderitemDao oidao = new OrderitemDao();
 	private CartDao cdao = new CartDao();
 	// 添加订单
 	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		HttpSession session=request.getSession(); 
 	 	  Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
 	 	  String uid=String.valueOf(user.get("id")) ;
		 int i=odao.insert(uid);
     	if(i!=0) {
     		oidao.insert(uid);
 		  
 		cdao.deleteByUid(uid);
 		response.getWriter().append("{\"code\":\"1\"}");
     	}
     
 		
 	}
 	// 查询新增的订单
 	protected void query(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
 		HttpSession session=request.getSession(); 
 	 	  Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
 	 	  String uid=String.valueOf(user.get("id")) ;
  		Map<String, Object> ret = new HashMap<>();
 		Map<String, Object> orders = odao.queryNewOrders(uid);
     	ret.put("orders", orders);
 		ret.put("orderitem", oidao.queryByOid("" + orders.get("id")));
 		 
 		print(response, ret);
 	}

}

