package learn.String;

import java.util.Random;

/**
 * Created by hukai on 2018/8/6.
 */
public class RandomTest {

    public static String generateStr(int length) {
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int x = (int)(Math.random() * 10) + 1;
            sb.append(String.valueOf(x));
        }
        return sb.toString();
    }

    public static void main(String[] args) {

//        int x1 = (int)(Math.random() * 100) + 1;//[1,100]之间的随机数
//
//        Random random = new Random(30);
//        for (int i = 0; i < 30; i++) {
//            System.out.print(random.nextInt(100) + " ");
//        }
//        System.out.println();
//        System.out.println("-------");
//        Random random1 = new Random(30);
//        for (int i = 0; i < 30; i++) {
//            System.out.print(random1.nextInt(100) + " ");
//        }

        System.out.println(generateStr(5));
    }
}
