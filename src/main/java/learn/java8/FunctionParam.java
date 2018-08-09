package learn.java8;

import learn.apache.commons.Student;

import java.util.Date;
import java.util.concurrent.Callable;

public class FunctionParam {
	
	
	
	
	private static Student findOk(Callable <Student> callable) {
		Student student = null;
		try {
			System.out.println("callable: " + Thread.currentThread().getName());

			student = callable.call();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(student.getAge() > 25 && student.getResult() > 100) {
			return student;
		}
		else {
			return null;
		}
		
	}
	
	
	public interface Handler {
		public <T> T Handler(T result);
	}
	
	
	public static void main(String[] args) {
		Student student = new Student("aaa", 29, 105, new Date());
		System.out.println("main: " + Thread.currentThread().getName());
		Student re = findOk(() -> {
			System.out.println("findOk: " + Thread.currentThread().getName());

			student.setAge(student.getAge() - 2);
			return student;
		});
		System.out.println(re);
	}






}
