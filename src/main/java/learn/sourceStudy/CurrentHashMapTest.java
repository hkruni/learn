package learn.sourceStudy;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hukai on 2018/7/26.
 */
public class CurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String ,String > map = new ConcurrentHashMap<>();
        map.put("111","222");

        map.mappingCount();
    }
}
