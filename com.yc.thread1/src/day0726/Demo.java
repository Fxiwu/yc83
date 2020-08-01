package day0726;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
/*
 *多线程+url==>多线程下载（迅雷下载)
 *
 *实现过程
 *1.单线程下载
 *2.单线程分块下载
 *多线程分块下载
 * 
 * 
 * 
 */
public class Demo {

	public static void main(String[] args) throws IOException {
		 URL url=new URL("https://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		 URLConnection conn=url.openConnection();
		 System.out.println("开始下载");
         InputStream in =conn.getInputStream();
         FileOutputStream  out=new FileOutputStream("E://其他/apache-tomcat-10.0.0-M7-windows-x64.zip"); 
		 byte[] buffer=new byte[1024];
		 int count;
		 while((count=in.read(buffer))>0) {
			out.write(buffer,0,count);
		 }
		 in.close();
		 out.close();
		 System.out.println("下载完成");

	}

}
