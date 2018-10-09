package learn.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hukai on 2018/9/12.
 */
public class Test1 {


    //使用预编译
    //模式分别为子模式p1(.*)和子模式p2(12)，其中p1中的量词匹配方式使用默认方式(贪婪模式)
    private static Pattern PG = Pattern.compile(".*12");

    //模式分别为子模式p1(.*)和子模式p2(12)，其中p1中的量词匹配方式使用默认方式(勉强模式)
    private static Pattern PR = Pattern.compile(".*?12");

    //模式分别为子模式p1(.*)和子模式p2(12)，其中p1中的量词匹配方式使用默认方式(占有模式)
    private static Pattern PP = Pattern.compile(".*+12");

    public static void main(String[] args) {
        //贪婪模式：
        //第一次匹配：吃入所有字符a12s%^&12去匹配子模式p1，匹配成功，但是这样就没有字符串去匹配子模式p2了。本轮匹配失败
        //第二次匹配：减少p1部分的匹配量，吐出最后一个字符，把字符串分割成a12s%^&1和2两个子字符串s1和s2。s1匹配p1，但s2不匹配p2。本轮匹配失败
        //第三次匹配：再次减少p1部分的匹配量，吐出两个字符，字符串被分割为a12s%^&和12两个子字符串s1和s2。s1/s2分别匹配p1/p2。停止尝试，返回true。
        Matcher m1 = PG.matcher("a12s%^&12");
        boolean b1 = m1.matches();
        System.out.println("b1: " + b1);    //返回：b1: true

        //勉强模式：
        //第一次匹配：p1由于是0或任意次，因此被忽略，用字符串去匹配p2,失败；本轮匹配失败。
        //第二次匹配：读入第一个字符a，尝试和p1匹配，匹配成功。剩余的字符串12s%^&12中前两个字符和p2也是匹配的。因此，停止尝试，返回true。
        Matcher m2 = PR.matcher("a12s%^&12");
        boolean b2 = m2.matches();
        System.out.println("b2: " + b2);   //返回：b2: true

        //占有模式
        //匹配开始时，读入所有字符串，和p1匹配成功，但没有剩余字符串去和p2匹配。因此匹配失败，返回false。
        Matcher m3 = PP.matcher("a12s%^&12");
        boolean b3 = m3.matches();
        System.out.println("b3: " + b3);   //返回：b3: false
    }

}
