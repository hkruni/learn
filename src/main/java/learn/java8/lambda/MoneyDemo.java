package learn.java8.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

class MyMoney {
	private final int money;

	public MyMoney(int money) {
		this.money = money;
	}

	/**
	 * 参数是一个函数接口，输入是Integer类型，输出的String类型
	 * @param moneyFormat
	 */
	public void printMoney(Function<Integer, String> moneyFormat) {
		System.out.println("我的存款" + moneyFormat.apply(this.money));
	}
}

public class MoneyDemo {

	public static void main(String[] args) {
		MyMoney me = new MyMoney(99999999);

		Function<Integer, String> moneyFormat = i -> new DecimalFormat("#,###")
				.format(i);
		
		me.printMoney(moneyFormat.andThen(s -> " 人民币" + s));
	}

}
