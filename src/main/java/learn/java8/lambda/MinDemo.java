package learn.java8.lambda;

import java.util.stream.IntStream;



public class MinDemo {

	public static void main(String[] args) {
		int[] nums = {33,55,-55,90,-666,90};
		
		int min = Integer.MAX_VALUE;
		for (int i : nums) {//求数组的最小值
			if(i < min) {
				min = i;
			}
		}
		
		System.out.println("jdk8之前 ： " + min);
		
		//jdk8实现
		int min2 = IntStream.of(nums).parallel().min().getAsInt();
		int min3 = IntStream.of(nums).min().getAsInt();
		System.out.println(min2);
		System.out.println(min3);
	}

}
