package learn.String;

import learn.annotation.demo.Student;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by hukai on 2018/7/29.
 */
public class HashSetTest {

    public static void main(String[] args) {
        Student s1 = new Student(1,"aaa",80);
        Student s2 = new Student(2,"aab",60);
        Student s3 = new Student(3,"ccc",120);
        Student s4 = new Student(4,"ddd",100);
        Student s5 = new Student(5,"eee",90);

        System.out.println(s3.hashCode());

        Set<Student> set = new HashSet<Student>(){
            {
                add(s1);
                add(s2);
                add(s3);
                add(s4);
                add(s5);
            }
        };

        Student s6 = new Student(5,"eee",90);

        s3.setName("xxx");
        System.out.println(s3.hashCode());

        System.out.println(set.contains(s3));
        System.out.println(set.contains(s6));

        for (Student student : set) {
            System.out.println(student);
        }

    }
}
