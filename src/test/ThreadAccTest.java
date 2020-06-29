package test;
class Account{
	private double balance;
	
	public Account(double balance) {
		this.balance = balance;
	}
	
	//存钱
	public void deposit(double amt) {
		if (amt >0) {
			synchronized (this) {
				balance+=amt;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+":存钱1000，余额为："+balance);
			}
			
		}
	}
}

class  Custom extends Thread{
	private Account acct;
	
	public Custom(Account acct) {
		this.acct = acct;
	}
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			acct.deposit(1000);
		}
	}
}

public class ThreadAccTest {
	public static void main(String[] args) {
		Account acct = new Account(0);
		Custom c1 = new Custom(acct);
		Custom c2 = new Custom(acct);
		c1.setName("客户甲");
		c2.setName("客户乙");
		
		c1.start();
		c2.start();
	}

}
