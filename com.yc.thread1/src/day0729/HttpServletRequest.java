package day0729;

import java.util.HashMap;
import java.util.Map;

public class HttpServletRequest {

	private String method;
	private String  requestUri;
	private String  protocol;
	//存放头域键值对的map集合
	private Map<String,String> headerMap=new HashMap<>();
	
   public HttpServletRequest(String requestText) {
	//完成对请求报文的解析
	   String[] lines=requestText.split("\\n");
	   String[] items=lines[0].split("\\s");
	   method=items[0];
	   requestUri=items[1];
	   protocol=items[2];
	   for(int i=1;i<lines.length;i++) {
		   lines[i]=lines[i].trim();
		   if(lines[i].isEmpty()) {
			   break;
		   }
		   items=lines[i].split(":");
		   headerMap.put(items[0], items[1].trim());
	   }
	   
   }
   
   /*
    * 获取请求方法
    * 
    */
   public String getMethod() {
	return method;
	   
   }
   /*
    * 获取请求资源路径
    * 
    */
   public String getRequestURI() {
	return requestUri;
	   
   }
   /*
    * 获取版本协议
    * 
    */
   public String getProtocol() {
	return protocol;
	   
   }
   /*
    * 获取头域值  键值对
    * 
    */
   public String getHeader(String name) {
	return headerMap.get(name);
	   
   }
   /*
    * 获取请求参数
    * 
    */
   public String getParamter(String name) {
	return null;
	   
   }
   /*
    * 获取请求cookie数据
    * 
    */
   public Cookie[] getCookies() {
	return null;
	   
   }
   

 
 
}
