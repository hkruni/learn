package learn.jvm;

import java.util.Scanner;

/**
 * Created by hukai on 2018/7/28.
 */
public class ThreadMonitor {

    final static Object ob = new Object();

    public static class T1 extends Thread{
        public void run(){
            synchronized (ob){
                System.out.println(Thread.currentThread().getName() + " started!");
                try {
                    Thread.sleep(10000);
                    ob.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class T2 extends Thread{
        public void run(){
            synchronized (ob){
                System.out.println(Thread.currentThread().getName() + " started!");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    public static void main(String[] args) throws InterruptedException {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();

        Thread.sleep(40000);

    }
}
