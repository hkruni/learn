package learn.guava;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sun.tools.javac.code.Attribute;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hukai on 2018/8/7.
 */
public class SplitterTest {

    public static void main(String[] args) {

        List<String> list1 = Lists.newArrayList("111","222","333","555");

        StringBuilder stringBuilder = new StringBuilder("hello");
        // 字符串连接器，以|为分隔符，同时去掉null元素
        Joiner joiner1 = Joiner.on("|").skipNulls();
        // 构成一个字符串foo|bar|baz并添加到stringBuilder
        stringBuilder = joiner1.appendTo(stringBuilder, "foo", "bar", null, "baz");
        System.out.println(stringBuilder);

        System.out.println("join list : " + joiner1.join(list1));//111|222|333|555




        Map<String, String> testMap = Maps.newLinkedHashMap();
        testMap.put("Cookies", "12332");
        testMap.put("Content-Length", "30000");
        testMap.put("Date", "2016.12.16");
        testMap.put("Mime", "text/html");
        // 用:分割键值对，并用#分割每个元素，返回字符串
        String returnedString = Joiner.on("#").withKeyValueSeparator(":").join(testMap);
        System.out.println("returnedString : " + returnedString);



        String s0 = "113|33|44|66|921";
        String s1 = "aaa()bbb()ccc()ddd";
        String []ss1 = s0.split("\\|");
        List<String> ss3 = Splitter.on("()").splitToList(s1);
        List<String> ss2 = Splitter.on("|").trimResults().splitToList(s0);
        Arrays.stream(ss1).forEach(System.out::println);
        ss2.stream().forEach(System.out::println);
        ss3.stream().forEach(System.out::println);


        System.out.println("String.join.list : " + String.join("()",list1));
        System.out.println("String.join.list : " + String.join("|",list1));
        System.out.println("String.join.list : " + String.join("\\",list1));
        System.out.println("String.join.list : " + String.join(".",list1));
        System.out.println("String.join.list : " + String.join("*",list1));
        System.out.println("String.join.list : " + String.join("+",list1));




    }
}
