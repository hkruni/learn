package learn.multiThread.completableFuture1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hukai on 2018/7/26.
 */
public class Test1 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {


        ExecutorService es = Executors.newFixedThreadPool(2);

        System.out.println("main 线程 :" + Thread.currentThread().getName());

        CompletableFuture<String> completableFuture1=CompletableFuture.supplyAsync(()->{
            System.out.println("1 线程 :" + Thread.currentThread().getName());
            //模拟执行耗时任务
            System.out.println("task1 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result1";
        },es);

        CompletableFuture<String> completableFuture2=CompletableFuture.supplyAsync(()->{
            System.out.println("2线程 :" + Thread.currentThread().getName());

            //模拟执行耗时任务
            System.out.println("task2 doing...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //返回结果
            return "result2";
        },es);

        CompletableFuture<Object> anyResult=CompletableFuture.anyOf(completableFuture1,completableFuture2);

        System.out.println("第一个完成的任务结果:"+anyResult.get());

        CompletableFuture<String > allResult=CompletableFuture.allOf(completableFuture1,completableFuture2)
                .thenApplyAsync((Void)->{
                    try {
                        String s1 = completableFuture1.get();
                        String s2 = completableFuture2.get();
                        return s1 + ":" + s2;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .exceptionally( e  -> {return null;});


        //阻塞等待所有任务执行完成
        String s1  = allResult.join();
        System.out.println("所有任务执行完成 :" + s1);


    }
}
