package learn.multiThread;

import java.util.concurrent.ConcurrentHashMap;

public class CurrentHashMapDemo {

    public static void main(String[] args) {

        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

        map.put("aa","111");

    }
}
