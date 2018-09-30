package learn.generic;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 泛型方法
 */
public class GeMethod {

    public static <T extends Number>T getMax(T a ,T b ,T c) {
        T max = a.doubleValue() > b.doubleValue() ? a : b;
        return max.doubleValue() > c.doubleValue() ? max : c;
    }

    public static <T>List<T> getMaxValue(List<T> list1,List<T> list2) {
        Set<T> set = new HashSet<>();
        set.addAll(list1);
        set.addAll(list2);
        return new ArrayList<>(set);
    }


    public static void main(String[] args) {
        Number r = GeMethod.getMax(35,12.6D,68L);
       // System.out.println(r);

        List<Integer> l1 = Lists.newArrayList(1,5,129,600,56,89);
        List<Integer> l2 = Lists.newArrayList(1,2,3,5,899,600,56,89);
        List<String > l4 = Lists.newArrayList("hello","aaa","bbb","ccc");
        List<String > l5 = Lists.newArrayList("hello","ddd","bbb","fff");
        List<Integer> l3 = GeMethod.getMaxValue(l1,l2);
        List<String> l6 = GeMethod.getMaxValue(l4,l5);
        //l3.forEach(System.out::println);
        l6.forEach(System.out::println);


    }

}
