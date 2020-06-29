package test;
//店员
class Clerk{

	private int productCount;
	
	public synchronized void produceProduct() {
		if(productCount < 20) {
			productCount++;
			System.out.println(Thread.currentThread().getName()+":开始生产第"+productCount+"个产品");
			notify();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public synchronized void consumeProduct() {
		if(productCount > 0) {
			System.out.println(Thread.currentThread().getName()+":开始消费第"+productCount+"个产品");
			productCount--;
			notify();
		}else {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

//生产者
class Productor extends Thread{
	private Clerk clerk;
	public Productor(Clerk clerk) {
		this.clerk = clerk;
	}
	
	@Override
	public void run() {
		System.out.println(getName() + ":开始生产产品。。。。。");
		while(true) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.produceProduct();
		}
	}
}

//消费者
class Customer extends Thread{
	private Clerk clerk;
	public Customer(Clerk clerk) {
		this.clerk = clerk;
	}
	
	@Override
	public void run() {
		System.out.println(getName() + ":开始消费产品。。。。。");
		while(true) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clerk.consumeProduct();
		}
	}
}

public class CustomAndProducetot {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Customer c1 = new Customer(clerk);
		Customer c2 = new Customer(clerk);
		Productor p = new Productor(clerk);
		c1.setName("消费者1");
		c2.setName("消费者2");
		p.setName("生产者");
		c1.start();
		c2.start();
		p.start();
	}
}
