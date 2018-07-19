package learn.java8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TestCollectors {




    public static void main(String[] args) {



        List<String> items =
                Lists.newArrayList("apple", "apple", "banana",
                        "apple", "orange", "banana", "papaya");


        items.add("xxx");
        items.stream().sorted((s1,s2) -> s1.length() - s2.length()).forEach(System.out::println);



    }
}
