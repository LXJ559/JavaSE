package model;

public class FactoryTest {
	public static void main(String[] args) {
		Car audi = CarFactory.getCar("奥迪");
		audi.run();
		Car byd = CarFactory.getCar("比亚迪");
		byd.run();
		
		Car audi1 = CarFactory.getAudi();
		audi1.run();
		Car byd1 = CarFactory.getBYD();
		byd1.run();
	}
}

interface Car{
	void run();
}

class Audi implements Car{

	@Override
	public void run() {
		System.out.println("奥迪车跑");
	}
	
}

class BYD implements Car{

	@Override
	public void run() {
		System.out.println("比亚迪车跑");
	}
	
}
/*
 * 简单工厂模式
 */
class CarFactory{
	//方式一
	public static Car getCar(String type) {
		if("奥迪".equals(type)) {
			return new Audi();
		}else if("比亚迪".equals(type)){
			return new BYD();
		}else {
			return null;
		}
	}
	
	//方式二
	public static Car getAudi() {
		return new Audi();
	}
	public static Car getBYD() {
		return new BYD();
	}
	
}

/*
 * 工厂方法模式
 */
interface CarFactory1{
	Car getCar();
}

class AudiFactory implements CarFactory1{

	@Override
	public  Car getCar() {
		return new Audi();
	}
	
}

class BYDFactory implements CarFactory1{

	@Override
	public Car getCar() {
		
		return new BYD();
	}
	
}