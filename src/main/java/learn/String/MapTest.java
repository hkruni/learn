package learn.String;

import learn.annotation.demo.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hukai on 2018/7/29.
 */
public class MapTest {

    public static void main(String[] args) {
        Student s1 = new Student(1,"aaa",80);
        Student s2 = new Student(2,"aab",60);
        Student s3 = new Student(3,"ccc",120);
        Student s4 = new Student(4,"ddd",100);
        Student s5 = new Student(5,"eee",90);

        Map<Integer,Student> map = new HashMap<Integer,Student>(){
            {
                put(s1.getId(),s1);
                put(s2.getId(),s2);
                put(s3.getId(),s3);
                put(s4.getId(),s4);
                put(s5.getId(),s5);
            }
        };
        System.out.println(map.get(10));

        s3.setId(10);//修改对象s3的id为10，再把这个对象插入map集合
        //Student s = map.get(3);
        //map.put(s.getId(),s);

        System.out.println(map.get(3));//Student{id=10, name='ccc', result=120}
        System.out.println(map.get(s3.getId()));//Student{id=10, name='ccc', result=120}

        //System.out.println(map.get(3) == map.get(10));//true，发现3和10为同一个对象

        //Student sr = map.remove(3);
        //System.out.println(sr);
        //System.out.println(map.get(10));



    }
}
