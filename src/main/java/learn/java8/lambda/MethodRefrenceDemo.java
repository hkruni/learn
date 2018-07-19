package learn.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntUnaryOperator;

class Dog {
	private String name = "����Ȯ";

	/**
	 * Ĭ��10�ﹷ��
	 */
	private int food = 10;

	public Dog() {

	}

	/**
	 * �������Ĺ��캯��
	 * 
	 * @param name
	 */
	public Dog(String name) {
		this.name = name;
	}

	/**
	 * ���У���̬����
	 * 
	 * @param dog
	 */
	public static void bark(Dog dog) {
		System.out.println(dog + "����");
	}

	/**
	 * �Թ��� JDK
	 * 
	 * Ĭ�ϻ�ѵ�ǰʵ�����뵽�Ǿ�̬������������Ϊthis��λ���ǵ�һ����
	 * 
	 * @param num
	 * @return ��ʣ�¶��ٽ�
	 */
	public int eat(int num) {
		System.out.println("����" + num + "�ﹷ��");
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

		// ��������
		Consumer<String> consumer = System.out::println;
		consumer.accept("���ܵ�����");

		// ��̬�����ķ�������
		Consumer<Dog> consumer2 = Dog::bark;
		consumer2.accept(dog);

		// �Ǿ�̬������ʹ�ö���ʵ���ķ�������
		// Function<Integer, Integer> function = dog::eat;
		// UnaryOperator<Integer> function = dog::eat;
		IntUnaryOperator function = dog::eat;
		
		// dog�ÿգ���Ӱ������ĺ���ִ�У���Ϊjava �����Ǵ�ֵ
		dog = null;
		System.out.println("��ʣ��" + function.applyAsInt(2) + "��");
		//
		// // ʹ����������������
		// BiFunction<Dog, Integer, Integer> eatFunction = Dog::eat;
		// System.out.println("��ʣ��" + eatFunction.apply(dog, 2) + "��");
		//
		// // ���캯���ķ�������
		// Supplier<Dog> supplier = Dog::new;
		// System.out.println("�������¶���" + supplier.get());
		//
		// // �������Ĺ��캯���ķ�������
		// Function<String, Dog> function2 = Dog::new;
		// System.out.println("�������¶���" + function2.apply("����"));

		// ����java�����Ǵ�ֵ���Ǵ�����
		List<String> list = new ArrayList<>();
		test(list);

		System.err.println(list);
	}

	private static void test(List<String> list) {
		list = null;
	}
}
