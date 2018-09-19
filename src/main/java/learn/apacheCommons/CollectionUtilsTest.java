package learn.apacheCommons;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by hukai on 2018/8/9.
 */
public class CollectionUtilsTest {

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("s2","s1","s3","s4");
        List<String> list2 = Lists.newArrayList("s1","s2","s3","s4");

        Student s1 = new Student("111",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s2 = new Student("222",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s3 = new Student("333",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s4 = new Student("111",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s5 = new Student("222",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s6 = new Student("444",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s7 = new Student("111",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s8 = new Student("999",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));

        List<Student> l1 = Lists.newArrayList(s1,s2,s3,s4,s5,s6,s7);
        List<Student> l2 = Lists.newArrayList(s3,s4,s8);

        System.out.println(CollectionUtils.isEmpty(list));//false,null或者size为0就是empty
        System.out.println(CollectionUtils.isEqualCollection(list,list2));//true,只比较内容，不管顺序
        System.out.println(CollectionUtils.isEqualCollection(l1,l2));//false 需要重写equals和hashcode方法

        CollectionUtils.addAll(list,new String[]{"111","999"});
        System.out.println(l1.size());//7
        System.out.println(list.size());//6

        //l1.retainAll(l2);//l1和l2的交集保存在l1
        //l2.stream().forEach(System.out::println);

        System.out.println(CollectionUtils.containsAny(l1,l2));//l1随便包含l2其中一个就返回true

        Collections.replaceAll(l1,s1,s2);
        l1.get(1).setName("jingdong");
        l1.stream().forEach(System.out::println);



    }
}
