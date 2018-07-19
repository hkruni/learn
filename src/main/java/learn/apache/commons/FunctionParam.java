package learn.apache.commons;

import java.util.Date;
import java.util.concurrent.Callable;

public class FunctionParam {
	
	
	
	
	private static Student findOk(Callable <Student> callable) {
		Student student = null;
		try {
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
		Student re = findOk(() -> {
			student.setAge(student.getAge() - 2);
			return student;
		});
		System.out.println(re);
	}






}
