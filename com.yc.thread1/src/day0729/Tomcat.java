package day0729;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
 

public class Tomcat {

	//Servlet容器
	private HashMap<String,Servlet> servletMap;
	public void startup() throws IOException {
		//服务器初始化Servlet容器==>map集合==》URL:servlet对象
		
		servletMap=new HashMap<>();
		servletMap.put("/photo/hello",new HelloServlet());
		//
		//
		//
		 ServerSocket tomact=new ServerSocket(8080);
		 System.out.println("tomact服务器启动完成，监听端口8080");
	     boolean running=true;
	     while(running) {
	    	  Socket socket=tomact.accept();
	    	  new Thread() {
	    		  public void run() {
	    			  try {
	    				  System.out.println("接收到请求");
	    					 InputStream in=socket.getInputStream();
	    					 OutputStream out=socket.getOutputStream(); 
	    					 byte[] buffer=new byte[1024];
	    					 int count;
	    					 count=in.read(buffer);
	    					 if(count>0) {
	    						 //打印请求报文
	    						 String requestText=new String(buffer,0,count);
	    						 System.out.println(requestText);
	    					      /*
	    					       * 解析出请求资源路径
	    					       * 
	    					       */
	    						 HttpServletRequest request=buildRequest(requestText);
	    						 HttpServletResponse response=new HttpServletResponse(out);
	    						 //使用资源地址区分动态请求和静态请求
	    						 //使用资源地址Servlet
	    						 String uri=request.getRequestURI();
	    						 Servlet servlet=servletMap.get(uri);
	    						 if(servlet!=null) {
	    							 //使用Servlet处理动态请求
	    							 servlet.service(request, response);
	    						 }else {
	    							 //如果没有找到对应的servlet对象，那么将其视为静态请求
	    							 //处理资源是否存在，如果不存在返回404
	    							 processStaticRequest(request,out);
	    						 }
	    						 
	    			  }
	    					 socket.close();
	    			  }catch(IOException e) {
	    				  e.printStackTrace();
	    			  }
	    		  }
  
	    	  }.start();
	     }
		
		  
		   tomact.close();
		
		}
	/*
	 * 解析请求对象
	 */
	private HttpServletRequest buildRequest(String requestText) {
		// TODO Auto-generated method stub
		return new HttpServletRequest(requestText);
	}
	/*
	 * 处理静态请求
	 */
	private void processStaticRequest(HttpServletRequest request, OutputStream out) throws IOException  {
		 String webpath=request.getRequestURI();
		 String contentType;
		 //结果码
		 int statusCode=200;
		 //定义磁盘路径
		 String path="E:/apache-tomcat-8.5.54-windows-x64/apache-tomcat-8.5.54/webapps/"+webpath;
		 File file=new File(path);
		 if(!file.exists()) {
			 statusCode=404;
			 path="E:/apache-tomcat-8.5.54-windows-x64/apache-tomcat-8.5.54/webapps/photo/404.html" ;
		 
		 }
         if(webpath.endsWith(".js")) {
			 contentType="application/javascript; charset=utf-8 ";
		 }else if(webpath.endsWith(".css")) {
			 contentType="text/css; charset=utf-8";
		 }else {
			 contentType="text/html; charset=utf-8";
		 }

		 //响应头行
	 out.write("HTTP/1.1 200 OK\n".getBytes());
	 //响应头域
	 out.write("ContentType:text/html; charset=utf-8\n".getBytes());
	 //空行CRLF
	 out.write("\n".getBytes());
	 //实体
//	 out.write("<h1>Hello</h1>".getBytes());
	 FileInputStream fis=new FileInputStream(path);
	 byte[]buffer=new byte[1024];
	 int count;
	  while((count=fis.read(buffer))>0) {
		  out.write(buffer,0,count);
	  }
	  fis.close();
		
	}
 
	public void shutdown() {
		
	}
	public static void main(String[] args) throws IOException {
		 new Tomcat().startup();
	}
}
