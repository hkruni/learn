package learn.java8.Stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


/**
 * 1.所有操作都是链式调用，一个元素只迭代一次
 * 2.每一个新的操作都会返回一个新的流
 * 3.有状态操作会单独处理
 * 4.并行环境下，有状态中间操作不一定能并行操作
 * */
public class RunStream {

	public static void main(String[] args) {
		Random random = new Random();
		Stream<Integer> stream = Stream.generate(() -> random.nextInt())
				.limit(500)
				.peek(s -> print("peek: " + s))
				.filter(s -> {
					print("filter: " + s);
					return s > 1000000;
				})
				.sorted((i1, i2) -> {//有状态操作
					print("peek1 " + i1 + ", " + i2);
					return i1.compareTo(i2);
				})
				.peek(s -> {
					print("peek2: " + s);
				}).parallel();

		stream.count();
	}

	/**
	 *
	 * @param s
	 */
	public static void print(String s) {
		// System.out.println(s);
		System.out.println(Thread.currentThread().getName() + " > " + s);
		try {
			TimeUnit.MILLISECONDS.sleep(5);
		} catch (InterruptedException e) {
		}
	}

}
