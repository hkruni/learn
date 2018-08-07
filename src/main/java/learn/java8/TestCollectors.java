package learn.java8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestCollectors {




    public static void main(String[] args) {



        List<String> items =
                Lists.newArrayList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");

        items.add("xxx");

        //所有元素排序
        items.stream().sorted((s1,s2) -> s1.length() - s2.length()).forEach(System.out::println);
        //所有元素排序（方法2）
        items.stream().sorted(Comparator.comparing(s -> s.length())).forEach(System.out::println);
        //输出长度最小的元素
        System.out.println(items.stream().min(Comparator.comparing(s -> s.length())).get());






    }
}
