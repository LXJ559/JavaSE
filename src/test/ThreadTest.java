package test;

public class ThreadTest {

	public static void main(String[] args) {
		Calculnum calculnum = new Calculnum();
		Thread t1 = new Thread(calculnum);
		t1.setName("线程一");
		t1.start();

		Thread t2 = new Thread(calculnum);
		t2.setName("线程二");
		t2.start();
	}
}

class Calculnum implements Runnable {
	//当实现runnable接口时不需要加static修饰变量，继承Thread需要加上
	private int ticket = 100;
	//当实现runnable接口时不需要加static修饰变量，继承Thread需要加上
	//任何一个对象都可以作为锁，只有这个对象是唯一的，即只new一次，就能保证安全
	private Object obj = new Object();
	@Override
	public void run() {
		while (true) {
			//方式一：同步代码块，包含的是共享数据设计的代码，即ticket,一般用this当前对象代替锁，
			//不管怎样都要保证对象唯一
			synchronized (obj) {
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
			}
			
		}
	}
	
	//方式二：同步方法，建议使用同步代码块
//	@Override
//	public void run() {
//		while (true) {
//			show();
//		}
//	}
	//同步监视器：锁为this,当前对象，继承Thread实现对线程，要加上static
	@SuppressWarnings("unused")
	private synchronized void show() {
		if (ticket > 0) {
			try {
				//使得当前线程阻塞，但是并没有释放锁
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":票号为" + ticket);
			ticket--;
		} 
	}

}
