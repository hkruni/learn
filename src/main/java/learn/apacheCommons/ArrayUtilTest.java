package learn.apacheCommons;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtilTest {


    /**
     * 删除数组
     * @param str元素
     * @param ele
     * @return
     */
    public static String[] removeAll(String []str1,String ele) {

        int count = 0;
        for(int i = 0;i < str1.length;i++) {
            if(str1[i].equals(ele)) {
                count+=1;
            } else {
                str1[i-count] = str1[i];
            }
        }
       return Arrays.copyOf(str1,str1.length - count);
    }


    public static boolean contains(Integer [] array,int x) {
        return ArrayUtils.contains(array,x);
    }



    public static void main(String[] args) {

        Integer [] a = {1,2,3,4,5,9,2365,89};
        System.out.println(contains(a,2365));


        String []s = {"ee","aa","bb","ee","sf","gg","ee","hh","00","zz","ii","dd","ee"};
        List<String> list = Lists.newArrayList("123","456","789","10");

        String []s2 = new String[list.size()];
        list.toArray(s2);
        Arrays.stream(s2).forEach(System.out::println);

        System.out.println("---------查找------------");
        System.out.println(Arrays.binarySearch(s,"ee"));//2
        System.out.println(ArrayUtils.indexOf(s,"ee"));//2

        System.out.println("------------添加-------------");
        System.out.println(Arrays.toString(ArrayUtils.add(s,"oo")));//末尾添加元素oo
        System.out.println(Arrays.toString(s));//原数组不变
        System.out.println(Arrays.toString(ArrayUtils.add(s,1,"76")));//在第一个位置添加76
        System.out.println(Arrays.toString(ArrayUtils.removeElement(s,"ee")));//删除第一个ee
        System.out.println(Arrays.toString(s));//原数组不变
        String [] re = removeAll(s,"ee");
        System.out.println(Arrays.toString(re));

        System.out.println("----------反转等--------------");
        ArrayUtils.reverse(s);//改变原数组的顺序
        System.out.println(Arrays.toString(s));
    }
}