package learn.java8;

import com.google.common.collect.Lists;
import learn.annotation.demo.Student;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Test1 {

    public static void main(String[] args) {


        Student s1 = new Student(1,"a",100);
        Student s2 = new Student(2,"bb",80);
        Student s3 = new Student(3,"ccc",70);
        Student s4 = new Student(4,"dddd",90);
        Student s5 = new Student(5,"eeee",60);
        Student s6 = new Student(20,"eeee",70);
        Student s7 = new Student(5,"eeee",60);
        Student s8 = new Student(8,"eeee",30);
        Student s9 = new Student(10,"eeee",80);
        Student s10 = new Student(6,"eeee",90);
        Student s11 = new Student(5,"eeee",50);

        List<Student> list = Lists.newArrayList(s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11);

        //提取name然后打印到控制台
        list.stream().map(Student::getName).forEach(System.out::println);

        //统计ID>1的对象个数
        System.out.println(list.stream().filter(s -> s.getId() > 1).count());

        //过滤出id>1的对象，提取result，然后求和
        System.out.println(list.stream().filter(s -> s.getId() > 1).mapToInt(Student::getResult).sum());

        //每个对象的result+100，然后打印输出
        list.stream().map(s -> s.getResult() + 100).forEach(System.out::println);
        //这种写法会改变list列表的数据
        //list.stream().map(s -> {s.setResult(s.getResult() + 100); return s;}).forEach(System.out::println);

        //排序,只取前两个
        list.stream().sorted((a,b) -> a.getResult() - b.getResult()).limit(2).forEach(System.out::println);
        //排序第二种写法
        Collections.sort(list,(a,b) -> a.getResult() - b.getResult());
        list.stream().map(Student::getName).limit(2).forEach(System.out::println);

        Map<Integer,List<Student>> ll = list.stream().collect(Collectors.groupingBy(Student::getId));
        ll.get(5).forEach(System.out::println);




    }
}
