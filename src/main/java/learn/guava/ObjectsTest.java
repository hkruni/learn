package learn.guava;

import learn.annotation.demo.Student;

import java.util.Objects;

/**
 * Created by hukai on 2018/8/7.
 */
public class ObjectsTest {

    public static void main(String[] args) {
        Student s1 = new Student(1,"aaa",100);
        Student s2 = new Student(1,"aaa",100);
        System.out.println(Objects.equals(s1,s2));
        Objects.hashCode(s1);

        if (s1 != null && s2 != null && Objects.equals(s1,s2)) {
            System.out.println("11");
        } else {
            System.out.println("22");
        }

    }
}
