package learn.java8.lambda;

@FunctionalInterface //编译期间校验，这个接口有且只有一个要实现的方法
interface Interface1 {
	int doubleNum(int i);
	default int add(int x, int y) {
		return x + y;
	}
	static int sub(int x, int y) {
		return x - y;
	}
}

@FunctionalInterface
interface Interface2 {
	int doubleNum(int i);
	default int add(int x, int y) {
		return x + y;
	}
}

@FunctionalInterface
interface Interface3 extends Interface2, Interface1 {
	@Override
	default int add(int x, int y) {
		return Interface1.super.add(x, y);
	}
}

public strictfp class LambdaDemo1 {

	public static void main(String[] args) {
		Interface1 i1 = (i) -> i * 2;

		Interface1.sub(10, 3);//i1.sub是不允许的
		System.out.println(i1.add(3, 7));
		System.out.println(i1.doubleNum(20));

		//这种是最常见的写法
		Interface1 i2 = i -> i * 2;
		Interface1 i3 = (int i) -> i * 2;
		Interface1 i4 = (int i) -> {
			System.out.println("-----");
			return i * 2;
		};

	}

}
