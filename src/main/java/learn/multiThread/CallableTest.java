package learn.multiThread;

import java.util.concurrent.Callable;

public class CallableTest {

    public static void main(String[] args) {

        Callable<Integer> call = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                return sum;
            }
        };

        try {
            int x = call.call();
            System.out.println(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
