package learn.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hukai on 2018/8/7.
 */
public class SplitterTest {


    /**
     * 字符串收尾两端的空格去掉
     * @param src
     * @param seprator
     */
    public static void split(String src,String seprator){
        List<String> list = Splitter.on(seprator).trimResults().splitToList(src);
        list.forEach(System.out::println);
    }

    /**
     * 去掉分隔后的空字符串
     * @param src
     * @param seprator
     */
    public static void splitOmit(String src,String seprator){
        List<String> list = Splitter.on(seprator).omitEmptyStrings().splitToList(src);
        list.forEach(System.out::println);
    }


    public static void splitOmitResukt(String src,String seprator){
        List<String> list = Splitter.on(seprator).omitEmptyStrings().trimResults().splitToList(src);
        list.forEach(System.out::println);
    }




    public static void main(String[] args) {


        String s = "123 , 321,   453,,741,41321";
        //split(s,",");
        //splitOmit(s,",");
        splitOmitResukt(s,",");



        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");

        List<String> list1 = Lists.newArrayList("111","222","333","555");
        String s0 = "113|33|44|66|921";
        String s1 = "aaa()bbb()ccc()ddd";
        String []ss1 = s0.split("\\|");
        List<String> ss3 = Splitter.on("()").splitToList(s1);
        List<String> ss2 = Splitter.on("|").trimResults().splitToList(s0);
        Arrays.stream(ss1).forEach(System.out::println);
        ss2.stream().forEach(System.out::println);
        ss3.stream().forEach(System.out::println);


        //正则表达式的特殊字符串都会被处理掉
        System.out.println("String.join.list : " + String.join("()",list1));
        System.out.println("String.join.list : " + String.join("|",list1));
        System.out.println("String.join.list : " + String.join("\\",list1));
        System.out.println("String.join.list : " + String.join(".",list1));
        System.out.println("String.join.list : " + String.join("*",list1));
        System.out.println("String.join.list : " + String.join("+",list1));





    }
}
