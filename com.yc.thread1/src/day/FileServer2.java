package day;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer2 {
	 public static void main(String[] args) throws Exception {
		 ServerSocket server=new ServerSocket(8888);
	    
	  while (true){         
		  Socket socket=server.accept();
		  System.out.println("与客户端建立连接！");   
		 
	  new Thread() {
			 public void run() {
				 
				 
					 try{
 				            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
 				            String fileName = dataInputStream.readUTF();
				      
				            FileOutputStream fileOutputStream = new FileOutputStream("E:\\新建文件夹" + File.separator + fileName);
				            int c = -1;
				            while ((c = dataInputStream.read()) != -1) {
				                fileOutputStream.write(c);
				                fileOutputStream.flush();
				            }

				            System.out.println(fileName+"文件接收成功！");
				            dataInputStream.close();
				            fileOutputStream.close();
					 }catch(IOException e) {
						 e.printStackTrace();
					 }
					
				 
			 }
		 }.start();
	      
	  }
	 }
 }
	      
 