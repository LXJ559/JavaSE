package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class NumberThread implements Runnable{
	
	@Override
	public void run() {
		
		for (int i = 0; i <= 100; i++) {
			if (i % 2 ==0 ) {
				System.out.println(Thread.currentThread().getName()+ ":"+ i);
				
			}
		} 
		
	}
}

class NumberThread1 implements Runnable{
	
	@Override
	public void run() {
		
		for (int i = 0; i <= 100; i++) {
			if (i % 2 !=0 ) {
				System.out.println(Thread.currentThread().getName()+ ":"+ i);
				
			}
		} 
		
	}
}

public class ThreadPool {
	public static void main(String[] args) {
		//提供指定线程数量的线程池
		ExecutorService service = Executors.newFixedThreadPool(10);
		
		//ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;
		//设置线程池的属性
		//service1.setCorePoolSize(15);
		//service1.setKeepAliveTime(time, unit);
		
		//执行指定的线程操作，需要提供实现Runnable接口或者Callable接口实现类的对象
		service.execute(new NumberThread());//适合使用Runnable
		service.execute(new NumberThread1());//适合使用Runnable
//		service.submit();//适合使用Callable
		
		//关闭线程池
		service.shutdown();
	}
}
