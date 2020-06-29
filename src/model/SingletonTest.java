package model;
/*
 * 
 *  单例模式:
 */
public class SingletonTest {

	public static void main(String[] args) {
		ABC abc= ABC.getABC();
		ABC abc1= ABC.getABC();
		System.out.println(abc.equals(abc1));
		
	
		JDBC jdbc = JDBC.getJDBC();
		JDBC jdbc1= JDBC.getJDBC();
		System.out.println(jdbc.equals(jdbc1));
	}
	
}

/*
 * 饿汉式：先创建一个对象
 */
class JDBC{
	//构造器私有化，使得外部不能通过new创建对象
	private JDBC() {
		
	}
	//私有化，使得外部不能直接通过.属性的方式调用这个对象。否则下面的方法就没用了，
	//当然直接调用这个属性也可以达到目的，但一般不这样做。封装性。
	//加static  是因为下面返回的是static对象，这里要一致,否则编译不通过
	private static JDBC jdbc = new JDBC();
	
	//加staic 是因为不能通过外部new对象，只能通过类调用方法，类方法使用static声明
	public static JDBC getJDBC() {
		return jdbc;
	}
}

/*
 * 懒汉式:用的时候创建，这是线程不安全的，得修改让其安全
 */
class ABC{
	
	//构造器私有化，使得外部不能通过new创建对象
	private ABC() {
		
	}
	
	//私有化，使得外部不能直接通过.属性的方式调用这个对象。
	private static ABC abc = null;
	
	//加上synchronized变为线程安全的 
	public static  ABC getABC() {
		
		//这样效率高，可将关键字直接加在方法上，不过就是效率低
		if(abc == null) {
			synchronized (ABC.class) {
				if(abc == null) {
					abc = new ABC();
				}
			}
		}
		return abc;
	}
}
