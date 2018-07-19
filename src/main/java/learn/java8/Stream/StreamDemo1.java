package learn.java8.Stream;

import java.util.stream.IntStream;


/**
 * 流的概念：
 * 1.内部、外部迭代
 * 2.中间、终止操作
 * 3.中间操作和终止操作
 */
public class StreamDemo1 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };

		// 外部迭代,for while循环的方式
		int sum = 0;
		for (int i : nums) {
			sum += i;
		}
		System.out.println("外部迭代结果为：" + sum);

		// 使用stream的内部迭代，map属于中间操作，sum指的终止操作，返回类型Stream是中间操作，否则就是终止操作
		int sum2 = IntStream.of(nums).map(StreamDemo1::doubleNum).sum();
		System.out.println("内部迭代结果为：" + sum2);

		System.out.println("惰性求值就是终止没有调用的情况下，中间操作不会执行：");
		IntStream.of(nums).map(StreamDemo1::doubleNum);//这里并没有执行doubleNum方法，说明中间操作不会执行
	}

	public static int doubleNum(int i) {
		System.out.println("执行了乘以2");
		return i * 2;
	}

}
