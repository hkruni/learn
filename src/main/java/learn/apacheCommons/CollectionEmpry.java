package learn.apacheCommons;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CollectionEmpry {

    public static void main(String[] args) {
        List<String > list = Collections.emptyList();
        for (String s : list) {
            System.out.println("11111111111");
            System.out.println(s);
        }
    }
}
