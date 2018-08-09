package learn.java8.Stream;


import learn.annotation.demo.Student;

import java.util.Optional;

/**
 * Created by hukai on 2018/8/7.
 */
public class TestOptional {

    public static void main(String[] args) {

        Student student = new Student(1, "111",30);
        Student student2 = new Student(2, "222",50);
        Student student3 = null;

        Optional<Student> optional = Optional.empty();
        //System.out.println(optional.get()); 抛出NoSuchElementException: No value presen
        //返回optional包装的对象，如果为空就返回student
        System.out.println(optional.orElse(student));


        Optional<Student> op2 = Optional.of(student2);
        //不允许为空
        //Optional<Student> op3 = Optional.of(null);

        Optional<Student> op4 = Optional.ofNullable(student3);
        //get比如null判断没有好到哪去
        //System.out.println(op4.get().getResult());


    }
}
