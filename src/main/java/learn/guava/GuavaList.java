package learn.guava;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimaps;
import learn.apache.commons.Student;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class GuavaList {

	public static void main(String[] args) {
		
		System.out.println(Strings.isNullOrEmpty("\t"));//false
		
		System.out.println(Strings.padStart("abc", 5, 'x'));//xxabc
	
		System.out.println(Strings.commonPrefix("abcd2idjsdna","abcdw1sasas"));
		
		
		List<Student> list = Lists.newArrayList(
				new Student("aaaa", 12, 100, new Date()),
				new Student("bbbbb", 13, 200, new Date()),
				new Student("cc", 14, 300, new Date()),
				new Student("ccc", 15, 400, new Date())
				);
		
		Map<String, Collection<Student>> map = Multimaps.index(list, new Function<Student, String>() {
			public String apply(Student input) {
				return input.getName();
			}
			
		}).asMap();
		
		map.forEach((k,v) -> {
			System.out.println(k);
			v.forEach(x -> System.out.println(x.getAge()));
		});
		
	}

}
