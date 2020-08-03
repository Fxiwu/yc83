package web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Dao.CartDao;
import util.DBHelper;
 
@WebServlet("/cart.do")
public class CartServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    private CartDao cdao=new CartDao();
       //vart.do ?op=add&pid=???
	protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	@SuppressWarnings("unchecked")
 	     
      	HttpSession session=request.getSession(); 
 	  Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
 	  String uid=String.valueOf(user.get("id")) ;
	 
		System.out.println("=======UUU"+uid);
		String pid=request.getParameter("pid");
        if( cdao.update(uid, pid)==0) {
        	//结果为0说明该用户还没有添加过商品
        	cdao.insert(uid, pid);
        }
		response.getWriter().append("{\"msg\":\"购物车添加成功！\"}");
	}
	protected void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); 
	 	  Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
	 	  String uid=String.valueOf(user.get("id")) ;
	   
        List<?> list = cdao.queryByUid(uid);
		print(response, list);


	}
	//总价
	protected void total(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(); 
	 	  Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
	 	  String uid=String.valueOf(user.get("id")) ;
	   
	 	 Map<String, Object> list = cdao.total(uid);
	 	 
		print(response, list);


	}
	//删除商品
protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
		
	   String id=request.getParameter("id") ;
       
	   try {
		   cdao.del(id);
		   print(response,"删除成功");
			   
		} catch (IOException e) {
			print(response,"删除失败");
		}
	 
      
       
	}
//清空购物
protected void clear(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession(); 
	  Map<String,Object> user=(Map<String, Object>) session.getAttribute("loginedUser");
	  String uid=String.valueOf(user.get("id")) ; 	   
	try {
		   cdao.clear(uid);;
		   print(response,"清空购物车成功");
			   
		} catch (IOException e) {
			print(response,"清空购物车失败");
		}
	 
   
    
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
