package model;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * 动态代理：
 *根据加载到内存中的被代理类，动态的创建一个代理类及其对象
 *当通过代理类的对象调用方法时，动态的调用被代理类的同名方法 
 */

interface Human{
	String getBelife();
	void eat(String food);
}

//被代理类
class SuperMan implements Human{

	@Override
	public String getBelife() {
		
		return "i believe i can fly!";
	}

	@Override
	public void eat(String food) {
		System.out.println("我喜欢吃"+food);
	}
	
}
//代理类
class ProxyFactory{
	//调用此方法，返回一个代理类的对象
	public static Object getProxyInstance(Object obj) {//obj:被代理的对象
		MyinvocationHandler handler = new MyinvocationHandler();
		handler.bind(obj);
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), handler);
	}
}


class HumanUtil{
	
	public static void method1(){
		System.out.println("=======通用方法1=======");
	}
	
	public static void method2(){
		System.out.println("=======通用方法2=======");
	}
}

class MyinvocationHandler implements InvocationHandler{

	private Object obj;
	public void bind(Object obj) {
		this.obj = obj;
	}
	//当我们通过代理类的对象，调用方法a时，会自动地调用如下的方法：invoke
	//将被代理类要执行的方法a的功能就声明再invoke()中
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		HumanUtil.method1();
		
		//method:即为代理类对象调用的方法，此方法也就作为了被代理类对象要调用的方法
		//obj:被代理类的对象
		Object returnValue = method.invoke(obj, args);
		//上述方法的返回值就作为当前类中的invoke()的返回值
		
		HumanUtil.method2();
		return returnValue;
	}
	
}

public class DynamicProxyTest {

	public static void main(String[] args) {
		SuperMan superMan = new SuperMan();
		//proxyInstance:代理类的对象
		Human proxyInstance = (Human) ProxyFactory.getProxyInstance(superMan);
		String believe = proxyInstance.getBelife();
		System.out.println(believe);
		proxyInstance.eat("螺狮粉");
	}
}
