package test;

import java.util.concurrent.locks.ReentrantLock;

/*
 * lock解决线程安全的问题
 */
public class LockTest {

	public static void main(String[] args) {
		Windows windows = new Windows();
		Thread t1 = new Thread(windows);
		Thread t2 = new Thread(windows);
		Thread t3 = new Thread(windows);
		t1.setName("窗口一");
		t2.setName("窗口二");
		t3.setName("窗口三");
		t1.start();
		t2.start();
		t3.start();
	}
}

class Windows implements Runnable{

	private int ticket = 100;
	/*
	 *  lock与synchronized:
	 *  	相同：都用来解决线程安全
	 *  	不同：synchronized在执行完相应的代码后自动地释放同步监视器
	 *  		lock需要手动的启动同步，手动的结束同步
	 */
	private ReentrantLock lock =new ReentrantLock();
	@Override
	public void run() {
		while (true) {
			try {
				
				lock.lock();
				if (ticket > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":票号为" + ticket);
					ticket--;
				} else {
					break; 
				}
			} finally {
				lock.unlock();
			} 
		}
	}
	
}
