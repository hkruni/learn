package learn.multiThread.CountDownLatch;

import java.util.concurrent.*;

public class ConcurrentCountBarier {


    private static CyclicBarrier barrier = new CyclicBarrier(2);


    /**
     * 线程1计算1-5的加法
     */
    public static class Add implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            Thread.sleep(3000);
            Integer sum = 0;
            for (int i = 1; i <= 5 ; i++) {
                sum += i;

            }
            barrier.await();//等待所有线程都执行完后同时返回
            return sum;
        }
    }

    /**
     * 线程2计算1-5的乘法
     */
    public static class Multi implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            Thread.sleep(10000);
            Integer sum = 1;
            for (int i = 1; i <= 5 ; i++) {
                sum *= i;

            }
            barrier.await();
            return sum;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        FutureTask<Integer> futureAdd = new FutureTask<Integer>(new Add());
        FutureTask<Integer> futureMuiti = new FutureTask<Integer>(new Multi());
        executor.submit(futureAdd);
        executor.submit(futureMuiti);

        System.out.println("等待一会。。。。");

        Integer i = futureAdd.get();
        System.out.println(i);
        Integer j = futureMuiti.get();
        System.out.println(j);
        System.out.println(i + j);


    }


}
