package learn.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

class Dog {
	private String name = "啸天犬";

	/**
	 * Ĭ��10�ﹷ��
	 */
	private int food = 10;

	public Dog() {

	}

	/**
	 * 带参数的构造函数
	 * 
	 * @param name
	 */
	public Dog(String name) {
		this.name = name;
	}

	/**
	 * 狗叫，静态方法
	 * 
	 * @param dog
	 */
	public static void bark(Dog dog) {
		System.out.println(dog + "叫了");
	}

	/**
	 * 吃狗粮
	 * JDK默认把当前实例对象传入到非静态方法，参数名为this，位置是第一个参数
	 */
	public int eat(Dog this,int num) {
		System.out.println("吃了" + num + "斤狗粮");
		this.food -= num;
		return this.food;
	}

	@Override
	public String toString() {
		return this.name;
	}
}

public class MethodRefrenceDemo {

	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.eat(3);

		//->左边只有一个参数，右边的函数体页只有一个参数，可以采用简写的方式
		Consumer<String> consumer = System.out::println;
		consumer.accept("接收的数据");

		// 静态方法的方法引用
		Consumer<Dog> consumer2 = Dog::bark;
		consumer2.accept(dog);

		// 非静态方法，使用对象实例
		//Function<Integer, Integer> function = dog::eat;
		//UnaryOperator<Integer> function = dog::eat;
		IntUnaryOperator function = dog::eat;
		
		//设置为null对function也不会有影响
		dog = null;
		System.out.println("还剩下" + function.applyAsInt(2) + "斤");
		//
		//使用类名来方法引用
		BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
		System.out.println("还剩下" + eatFunction.apply(dog, 2) + "斤");

		// 构造函数的方法引用
		Supplier<Dog> supplier = Dog::new;
		System.out.println("创建了新对象" + supplier.get());

		//带参数的构造函数方法引用
		Function<String, Dog> function2 = Dog::new;
		System.out.println("创建了新对象" + function2.apply("旺财"));


		List<String> list = new ArrayList<>();
		test(list);

		System.err.println(list);
	}

	private static void test(List<String> list) {
		list = null;
	}
}
