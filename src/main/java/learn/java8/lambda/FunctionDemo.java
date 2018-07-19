package learn.java8.lambda;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.IntPredicate;

public class FunctionDemo {

	
	public static void main(String[] args) {


		IntPredicate predicate = i -> i > 0;
		System.out.println(predicate.test(-9));
		
		//
		// IntConsumer

		Consumer<String> consumer = s -> System.out.println(s);
		consumer.accept("输入的数据");

	}

}
