package learn.guava;

import com.google.common.collect.Lists;
import com.google.common.primitives.Longs;

import java.util.List;

public class LongsTest {

    public static void main(String[] args) {

        List<String> list  = Lists.newArrayList("123","456","789","23");

        List<Long> ll = Lists.newArrayList(Longs.stringConverter().convertAll(list));

        for (Long aLong : ll) {
            System.out.println(aLong);
        }

    }
}
