package day0726;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/*
 * 多线程分区下载
 *  1. 进度统计(顺便被打乱)


 *    2. 合并的时机, 要等到所有的块下载完成才能合并


 *    3. 时间的统计


 *    


 *    隐患: 对块数的限制 12M / 2M = 6
 	1024M / 2M = 512 个子线程
   千万别下大文件
 必须要进行块数的限制     downNums 不能超过 10
 * 
 */
public class demo2 {
	/*
	 * 当前正在下载块
	 */
	private int downNums=0;
	public static void main(String[] args) throws IOException, InterruptedException {
		new demo2().download();
	}
	//定义下载方法
	private void download() throws IOException, InterruptedException  {
		 URL url=new URL("https://mirrors.tuna.tsinghua.edu.cn/apache/tomcat/tomcat-10/v10.0.0-M7/bin/apache-tomcat-10.0.0-M7-windows-x64.zip");
		 URLConnection conn=url.openConnection();
		 System.out.println("开始下载");
		 String filename="E:\\其他\\分区下载/apache-tomcat-10.0.0-M7-windows-x64.zip";
		 long time=System.currentTimeMillis();
		 //获取文件大小
		 int filesize=conn.getContentLength();
		 //每块的大小(自定义2M)
		 int blocksize=1*1024*1024;
		 //计算块数
		 int  blocknums=filesize/blocksize;
		 if(filesize%blocksize>0) {
			 blocknums++;
		 }
		 
		 //分块下载
		 for(int i=0;i<blocknums;i++) {
			 /*
			  * 开启线程下载
			  */
			 downNums++;

			 /*
			  * 在此等待
			  * 
			  */
			 synchronized(this) {
				 //如果当前下载的块数大于0则继续等待
				 while(downNums>10) {
					 System.out.println("当前块数达到最大值");
					 wait();
				 }
			 }
			 int index=i;//jdk会自动对其加final
			 new Thread() {
				 public void run() {
					 
					 try {
						 //匿名类中访问的外部变量必须是final
						 System.out.println("第"+(index+1)+"块下载");
					 //在每次循环中获取一个连接对象
					URLConnection conn=url.openConnection();
						 InputStream in =conn.getInputStream();
					        FileOutputStream  out=new FileOutputStream( filename+index); 
							//开始的字节数
					        int beginBytes=index*blocksize;
					        //结束的字节数
					        int endBytes=beginBytes+blocksize;
					        //结束的字节数不能超过文件的大小
					        if(endBytes>filesize) {
					        	endBytes=filesize;
					        }
					        //跳过开始的字节数
					        in.skip(beginBytes);
					        /*
					         * 请下载当前块中的字节数
					         * 1.计数
					         * 2.判断
					         */
					        //当前下载到的位置
					        int position=beginBytes;
					        byte[] buffer=new byte[1024];
							 int count;
							 while((count=in.read(buffer))>0) {
								 if(position+count>endBytes) {
									 //计算超出的部分
									 int a=position+count-endBytes;
									 //减去超出的部分
									count=count-a;
								 }
								out.write(buffer,0,count);
								//更新下载位置（向前推进）
								position+=count;
								//如果下载位置已经到达该块结束位置
								if(position>=endBytes) {
									break;
								}
							 }
							 in.close();
							 out.close();
							 System.out.println("第"+(index+1)+"块结束下载");

							 //匿名类中访问外部类对象的方式是Demo.this
							 synchronized(demo2.this) {
								 demo2.this.downNums--;
								 //通知等待中的主线程，尝试完成合并
								 demo2.this.notify();
							 }
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				 }
			 }.start();
		 } 
		 synchronized(this) {
			 //如果当前下载的块数大于0则继续等待
			 while(downNums>0) {
				  
				 wait();
			 }
		 }
         //合并文件
		 marge(filename,blocknums);
		 /*
		  * 
		  * 清空临时保存的文件
		  */
//		 for(int i=0;i<blocknums;i++){
//			 
//		 }
		 
		 System.out.println( (System.currentTimeMillis()-time)/1000+"秒");
		 System.out.println("下载完成");

	}
	//合并文件
	public static void marge(String path,int filenum) throws IOException{
		FileOutputStream fos=new FileOutputStream(path);
		for(int i=0;i<filenum;i++) {
			FileInputStream fis=new FileInputStream(path+i);
			byte[] buffer=new byte[1024];
			int count;
			while((count=fis.read(buffer))>0) {
				fos.write(buffer,0,count);
			}
			fis.close();
			
		}
		fos.close();
	}

}
