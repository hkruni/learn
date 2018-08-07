package learn.java8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 变量引用
 */
public class VarDemo {

	public static void main(String[] args) {


		List<String> list = new ArrayList<>();
		list.add("788");
		System.out.println("123" + list);
		String s1 = "123";
		//s1 = "456";//这里编译出错，因为s1被lambda引用，所以已经是final
		Consumer<String> consumer = s -> System.out.println(s + list);
		list.add("Xx");
		consumer.accept("1211");
	}

}
