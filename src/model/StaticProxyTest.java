package model;
/*
 *  静态代理模式：设计模式，实际上就是创建被代理类对象，
 *  将它传给代理类的引用，代理类调用的方法就是被代理类调用的方法
 */
public class StaticProxyTest {
	public static void main(String[] args) {
		Server server = new Server();
//		ProxyServer proxyServer = new ProxyServer(server);
//		proxyServer.browse();
		Network work = new ProxyServer(server);
		work.browse();
				
	}
}

interface Network{

	void browse();
}

//被代理类
class Server implements Network{

	@Override
	public void browse() {
		System.out.println("真实的服务器访问网络");
	}
	
}

//代理类
class ProxyServer implements Network{
	//类似spring中的，声明接口的变量，通过构造器指明对象，而spring代替了构造器的功能。 
	private Network work;
	
	public ProxyServer(Network work) {
		this.work = work;
	}
	
	@Override
	public void browse() {
		work.browse();
		
	}
	
}