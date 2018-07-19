package learn.multiThread.ReentrantLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

    private static ReentrantLock lock = new ReentrantLock();

    //private static CyclicBarrier cyclicBarrier = new CyclicBarrier(100);

    private static long count = 0;



    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(() ->{
                lock.lock();
                count++;
                lock.unlock();
            });

        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(count);


    }
}
