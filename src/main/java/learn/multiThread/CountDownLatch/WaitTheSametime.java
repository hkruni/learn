package learn.multiThread.CountDownLatch;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class WaitTheSametime {

    public static class Add  implements  Runnable {

        private CyclicBarrier barrier = new CyclicBarrier(2);

        public Add(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            int sum = 0;
            for(int i = 0 ; i < 10 ; i ++){
                sum += i;
            }
            System.out.println("线程add的sum 执行结果是：" + sum );
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Multiplication  implements  Runnable {

        private CyclicBarrier barrier;

        public Multiplication(CyclicBarrier barrier) {
            this.barrier = barrier;
        }


        @Override
        public void run() {
            int sum = 1;
            for(int i = 1; i < 5 ; i ++){
                sum *= i;
            }
            System.out.println("线程Multiplication的sum 执行结果是：" + sum );
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static class CountSum implements Runnable {

        @Override
        public void run() {
            System.out.println("汇总结果");
        }
    }


    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2,new CountSum());
        Thread t1  = new Thread(new Add(barrier));
        Thread t2 = new Thread(new Multiplication(barrier));
        t1.start();t2.start();
    }
}
