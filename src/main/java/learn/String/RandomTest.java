package learn.String;

import java.util.Random;

/**
 * Created by hukai on 2018/8/6.
 */
public class RandomTest {

    public static void main(String[] args) {

        int x1 = (int)(Math.random() * 100) + 1;//[1,100]之间的随机数

        Random random = new Random(30);
        for (int i = 0; i < 30; i++) {
            System.out.print(random.nextInt(100) + " ");
        }
        System.out.println();
        System.out.println("-------");
        Random random1 = new Random(30);
        for (int i = 0; i < 30; i++) {
            System.out.print(random1.nextInt(100) + " ");
        }
    }
}
