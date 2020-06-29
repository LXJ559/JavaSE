package test;

/*
 * wait,notify使用必须使用在同步代码块或者同步方法中，不能用在lock中实现通信
 * wait,notify的调用者必须是同步代码块或者同步方法中的同步监视器，否则出现非法监视器异常
 */
class Number implements Runnable{

	private int num = 1; 
	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				//唤醒已经wait的阻塞线程,this一般不写
				this.notify();
				if (num <= 100) {
					System.out.println(Thread.currentThread().getName() + ":" + num);
					num++;
					try {
						//使得调用wait方法的线程进入阻塞状态,释放锁
						this.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					break;
				}
			}
		}
	}
	
}


public class ThreadCommuicateTest {
	public static void main(String[] args) {
		Number number = new Number();
		Thread t1 = new Thread(number);
		Thread t2 = new Thread(number);
		t1.setName("甲");
		t2.setName("乙");
		
		t1.start();
		t2.start();
	}
}
