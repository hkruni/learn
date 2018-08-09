package learn.java8;

import com.google.common.collect.Lists;
import learn.annotation.demo.Student;

import java.util.List;


/**
 * Created by hukai on 2018/8/7.
 */
public class Test3 {

    public static void main(String[] args) {
        Student s1 = new Student(1,"a",90);
        Student s2 = new Student(2,"bb",100);
        Student s3 = new Student(3,"ccc",70);
        Student s4 = new Student(4,"dddd",90);
        Student s5 = new Student(5,"eeee",120);
        Student s6 = new Student(20,"eeee",70);
        Student s7 = new Student(5,"eeee",60);
        Student s8 = new Student(8,"eeee",30);
        Student s9 = new Student(10,"eeee",80);
        Student s10 = new Student(6,"eeee",90);
        Student s11 = new Student(5,"eeee",50);

        List<Student> list = Lists.newArrayList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11);


        //转换为int流然后求总和
        System.out.println(list.stream().mapToInt(s -> s.getResult() * 10).sum());

        //转换为int流 取前三个，找出最大值
        System.out.println(list.stream().mapToInt(Student::getResult).limit(3).max().getAsInt());

        //找到第一个result为90的对象
        System.out.println(list.stream().filter(s -> s.getResult()==90).findFirst().orElse(new Student(0,"000",10)));

    }
;
}
