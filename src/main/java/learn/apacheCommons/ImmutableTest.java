package learn.apacheCommons;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class ImmutableTest {

    public static Pair<List<String>,Integer> getElement() {
        List<String > list = Lists.newArrayList("123","456","780");
        Integer count = 3;
        Pair<List<String>,Integer> pair  = Pair.of(list,3);
        return pair;
    }

    public static void main(String[] args) {
        Pair<List<String>,Integer> pair = getElement();
        List<String > list = pair.getLeft();
        list.forEach(System.out::println);
    }
}
