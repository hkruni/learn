package learn.apache.commons;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ArrayUtilTest {


    /**
     * 删除数组
     * @param str元素
     * @param ele
     * @return
     */
    public static String[] removeAll(String []str,String ele) {

        int count = 0;
        for(int i = 0;i < str.length;i++) {
            if(str[i].equals(ele)) {
                count+=1;
            } else {
                str[i-count] = str[i];
            }
        }
       return Arrays.copyOf(str,str.length - count);
    }




    public static void main(String[] args) {
        String []s = {"ee","aa","bb","ee","ff","gg","ee","hh","00","zz","ii","dd","ee"};

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