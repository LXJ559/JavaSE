package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/*
 * 实现callable创建线程，相比runnable有返回值和异常抛出,支持泛型
 */
class NumThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for (int i = 0; i <= 100; i++) {
			if (i % 2 ==0 ) {
				System.out.println(i);
				sum += i;
			}
		} 
		return sum;
	}
	
}

public class CallableTest {
	public static void main(String[] args) {
		NumThread numThread = new NumThread();
		//FutureTask 实现了RunnableFuture,RunnableFuture继承了Runnable和Future
		FutureTask<Integer> futureTask = new FutureTask<Integer>(numThread);
		new Thread(futureTask).start();
		try {
			//get方法的返回值FutureTask构造器参数callable实现类重写的call()的返回值
			Integer sum = futureTask.get();
			System.out.println("总和为："+ sum);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
