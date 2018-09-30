package learn.generic;

import learn.annotation.demo.Student;

import java.util.HashMap;
import java.util.Map;

public class MapUtil<K,V> {

    Map<K,V> maps = new HashMap<>();

   public void set(K key,V value) {
       maps.put(key,value);
   }

   public V get(K key) {
       return maps.get(key);
   }

    public static void main(String[] args) {
        MapUtil map = new MapUtil();
        map.set("123",new Student(1,"123",3));
        Student s = (Student) map.get("123");
        System.out.println(s);
    }
}
