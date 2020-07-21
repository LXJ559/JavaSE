package test;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;




/* 
 * 重写toString():String File Date 包装类
 *  多态父类方法若不被重写，则调用父类方法，若被重写，调用子类方法
 *  接口中的方法都是 public abstract 抽象的 静态的,因为接口没有构造器，
 *  不能通过当前对象调用，但是可以通过字类的new 对象调用。
 * 	接口中的变量 都是全局常量：public static final
 */
public class ExtendTest extends Father implements Fly{
	public static void main(String[] args) {
		ExtendTest p = new ExtendTest();
		p.eat(); 
		Fly fly = new ExtendTest();
		fly.eat();
		fly.method1();
		
		//底层是char[] 数组，无参长度是16，有参的是n+16,当添加超过这么多，扩容2倍+2，线程安全
		@SuppressWarnings("unused")
		StringBuffer  sb = new StringBuffer();
		
		//ArrayList初始化容量时，空参默认为10，实参是n就是长度为n的Object[]数组，大于n时扩容1.5倍，将原数据复制到新数组
		//，add时初始化容量，没有add时为{}
		//vector,线程安全，初始化就有容量，10，扩容2倍
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		
		int i = 6; //0000 0101| 0000 0010 =0000 0111
		//>>>i表示右移i位，高位补0；
		i |= i>>>1;  //0000 0110|0000 0011 = 0000 0111  7
		i |= i>>>2;	 //0000 0111|0000 0001 = 0000 0111  7
		i |= i>>>4;  //0000 0111|0000 0000 = 0000 0111  7
		i |= i>>>8;  //0000 0111|0000 0000 = 0000 0111  7
		i |= i>>>16; //0000 0111|0000 0000 = 0000 0111  7
		System.out.println((i < 0) ? 1 : i + 1);
		//HashMap初始化容量时，空参构造器为16的Node<K,V>[]数组，带参数时，不是16，是2的整次幂
		//具体多少次幂，看上面的算法，put时才初始化容量，没有put时为{}。
		//当size长度大于threshold临界值， 扩容2倍,
		//某个元素的链表，当遍历>=7时转为树结构，但是如果数组容量没有超过64，则扩容，扩容之后，链表上的元素转移到数组中
		//Hashtable初始化11，扩容2倍+1
		Map<Integer,Integer> map = new HashMap<Integer,Integer>(15);  //16
		map.put(1, 1);
		Set<Integer> set = map.keySet();
		//迭代器模式，设计模式的一种
		Iterator<Integer> itor = set.iterator();
		while(itor.hasNext()) {
			System.out.println(itor.next());
		}
	}
	//既重写了父类的eat方法，又实现了接口的eat方法
	//若没有重写，则默认调用父类的方法
	public void eat() {
		System.out.println("吃饭喝水");
	}
	
}

class Father{
	@SuppressWarnings("unused")
	private String name;
	@SuppressWarnings("unused")
	private int age;
	

	//private方法是不能被重写的
	public  void eat() {
		System.out.println("吃饭");
	}
}

interface Fly{
	
	void eat() ;
	//只能通过接口调用，不能通过实现类调用
	static void method() {
		System.out.println("静态方法");
	}
	//不加default会报错，不加默认是抽象方法,默认方法通过实现类的对象调用 
	default void method1() {
		System.out.println("默认方法");
	}
}
