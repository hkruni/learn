package learn.java8.lambda;

public class ThreadDemo {

	public static void main(String[] args) {

		//jdk8之前
		Runnable target = new Runnable() {
			@Override
			public void run() {
				System.out.println("ok");
			}
		};
		new Thread(target).start();

		//jdk8 lambda
		Object target2 = (Runnable)() -> System.out.println("ok");
		Runnable target3 = () -> System.out.println("ok");
		//false 说明target2和target3是不同的对象实例
		System.out.println(target2 == target3);
		
		new Thread(target3).start();
	}

}
