package day0730;

public class PostServlet extends HttpServlet{

	public void doPost(HttpServletRequest request,HttpServletResponse response) {
		String name=request.getParamter("name");
		System.out.println("===========================");
		response.getWriter().append("post,name="+name);
		//拓展  完成post请求中实体的参数解析
	     
		
	}
	
	
}
