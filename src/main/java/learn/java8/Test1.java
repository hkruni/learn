package learn.java8;

import com.google.common.collect.Lists;
import learn.annotation.demo.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String[] args) {


        Student s1 = new Student(1,"a",100);
        Student s2 = new Student(2,"bb",85);
        Student s3 = new Student(3,"ccc",70);
        Student s4 = new Student(4,"dddd",90);
        Student s5 = new Student(5,"eeee",60);
        Student s6 = new Student(5,"eeee",60);

        List<Student> list = Lists.newArrayList(s1,s2,s3,s4,s5,s6);

        //提取name然后打印到控制台
        list.stream().map(Student::getName).forEach(System.out::println);
        //统计ID>1的对象个数
        System.out.println(list.stream().filter(s -> s.getId() > 1).count());
        //过滤出id>1的对象，提取result，然后求和
        System.out.println(list.stream().filter(s -> s.getId() > 1).mapToInt(Student::getResult).sum());
        //每个对象的result+100，然后打印输出
        list.stream().map(s -> {s.setResult(s.getResult() + 100); return s;}).forEach(System.out::println);

        //排序,只取前两个
        list.stream().sorted((a,b) -> a.getResult() - b.getResult()).limit(2).forEach(System.out::println);

        //第二种写法
        Collections.sort(list,(a,b) -> a.getResult() - b.getResult());
        list.stream().map(Student::getName).forEach(System.out::println);
    }
}
