package learn.apache.commons;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.Date;
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
        Student s3 = new Student("111",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s4 = new Student("222",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));
        Student s5 = new Student("555",20,30,DateTimeUtil.strToDate("2010-01-01 11:11:11","yyyy-MM-dd HH:mm:ss"));

        List<Student> l1 = Lists.newArrayList(s2,s1);
        List<Student> l2 = Lists.newArrayList(s3,s4,null,s5);




        System.out.println(CollectionUtils.isEmpty(list));
        System.out.println(CollectionUtils.isEqualCollection(list,list2));
        System.out.println(CollectionUtils.isEqualCollection(l1,l2));

        CollectionUtils.addAll(list,new String[]{"111","999"});
        System.out.println(l1.size());
        System.out.println(list.size());
    }
}
