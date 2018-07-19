package learn.multiThread.CountDownLatch;

import java.util.concurrent.CountDownLatch;

/**
 * 模拟两个线程同时执行
 */
public class StudentRunTheSameTime implements Runnable {

    private CountDownLatch latch;

    private String name;

    public StudentRunTheSameTime(String  name ,CountDownLatch latch) {
        this.name = name;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            System.out.println("线程 ： " + name + "开始执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(1);
        Thread t1 = new Thread(new StudentRunTheSameTime("小明",latch));
        Thread t2 = new Thread(new StudentRunTheSameTime("小红",latch));
        t1.start();
        t2.start();
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
        long end = System.currentTimeMillis();
        System.out.println(end - start + "毫秒");

    }


}
