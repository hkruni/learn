package learn.java8.lambda;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class FunctionDemo {


	private static int getResult(Predicate<Integer> predicate,int x) {
		boolean result = predicate.test(x);
		if (result) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public static void main(String[] args) {


		IntPredicate predicate = i -> i > 0;
		System.out.println(predicate.test(-9));

		System.out.println(getResult(i -> i % 2 == 1,8));


		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("输入的数据");



	}

}
