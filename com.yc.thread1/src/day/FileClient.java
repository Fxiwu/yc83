package day;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

public class FileClient {
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
			            //使用DataOutputStream
			            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			            //向服务器端传文件名
			            dataOutputStream.writeUTF(file.getName());
			            dataOutputStream.flush();//刷新流，传输到服务端

			            //向服务器端传文件，通过字节流
			            //字节流先读取硬盘文件
			            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
			            
			            int c = -1;
			            while ((c=bufferedInputStream.read())!=-1) {
			                //将读取的文件以字节方式写入DataOutputStream，之后传输到服务端
			                //这里也可以使用byte[]数据进行读取和写入
			                dataOutputStream.write(c);
			                dataOutputStream.flush();
			            }
				 }catch(IOException e) {
					 e.printStackTrace();
				 }
				
			 
		 }
	 }.start();
	 
}

}
