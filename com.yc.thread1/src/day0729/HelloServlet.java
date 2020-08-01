package day0729;

import java.io.PrintWriter;

public class HelloServlet extends HttpServlet {
       public void doGet(HttpServletRequest request,HttpServletResponse response){
    	   PrintWriter out=response.getWriter();
    	   //输出到页面
    	   out.print("<h1>hello</h1>");
    	   System.out.println("hello world!"); 
       }
}
