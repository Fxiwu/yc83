package day0725;

public class demo2 {

	public static void main(String[] args) {
        //匿名内部类创建线程
		Thread t1=new Thread("线程1"){
			//类定义，匿名方式
			public void run() {
				for(int i=0;i<10000;i++) {
				System.out.println(Thread.currentThread().getName()+":"+i);
			}
			}
			
		};
		Thread t2=new Thread("===============================线程2"){
			public void run() {
				for(int i=0;i<2000;i++) {
				System.out.println(Thread.currentThread().getName()+":"+i);
			        try {
			        	if(i==600) {
			        		t1.join();
			        	}
						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t1.start();
		t2.start();
	}

}
