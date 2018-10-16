package learn.guava;

import com.google.common.base.Splitter;

import java.util.List;

public class Split {


    public static void main(String[] args) {

        List<String> list = Splitter.on(",").splitToList("123131,890");
        for (String s : list) {
            System.out.println(s);
        }

        List<String> list2 = Splitter.on(",").splitToList(null);
        System.out.println(list2.size());
    }
}
