package learn.java8.Stream;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


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
				.sorted((i1, i2) -> {
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
