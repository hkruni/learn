package learn.apache.commons;

import com.google.common.collect.Lists;
import learn.json.jackson.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hukai on 2018/9/6.
 */
public class JavaCollection {

    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        User u = new User();
        u.setUsername("123");
        list.add(u);
        System.out.println(u);
        test(list);
        User u1 = list.get(0);
        System.out.println(u);

        strListToLong();

    }

    public static  void test(List<User> l) {
        User u = l.get(0);
        u.setPassword("1998");
    }

    public static void  strListToLong() {
        List<String> list = Lists.newArrayList("123", "456", "789", "256");
        List<Long> longs = list.stream().map(s -> Long.valueOf(s.trim())).collect(Collectors.toList());
        longs.stream().forEach(System.out::println);
    }
}
