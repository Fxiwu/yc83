package day;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class FileClient2 {
	public static void main(String[] args) throws UnknownHostException, IOException {

	//只要new出Socket 就会立即和服务器进行连接
	//默认使用随机端口
	Socket socket=new Socket("127.0.0.1",8888);
	
	//我的地址
	InetAddress myAddress=socket.getInetAddress();
   SocketAddress otherAddress=socket.getRemoteSocketAddress();
   System.out.println("我的地址"+myAddress);
    System.out.println("客户端的地址"+otherAddress);

    File file = new File("E://xxxx.txt");
	 new Thread() {
		 public void run() {
			 byte[] buffer=new byte[1024];
			 int count;
			 
				 try{
					 OutputStream outputStream = socket.getOutputStream();
 			            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
 			            dataOutputStream.writeUTF(file.getName());
			            dataOutputStream.flush(); 
			            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			            System.out.println("发送文件"+file.getName());
			            int c = -1;
			            while ((c=bufferedInputStream.read())!=-1) {
			                 
			                dataOutputStream.write(c);
			                dataOutputStream.flush();
			                
			            }
		            	System.out.println("发送文件成功" );

			            outputStream.close();
			            dataOutputStream.close();
				 }catch(IOException e) {
					 e.printStackTrace();
				 }
				
			 
		 }
	 }.start();
	 
}

}
