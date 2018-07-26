package learn.multiThread.completableFuture1;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hukai on 2018/7/26.
 */
public class GetValue {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(2);
        CompletableFuture<Integer> future = new CompletableFuture<>();

        es.execute(new GetYear(future));

        System.out.println(future.get());
    }

    private static class GetYear implements Runnable {

        private CompletableFuture completableFuture;

        public GetYear(CompletableFuture completableFuture) {
            this.completableFuture =completableFuture;
        }

        @Override
        public void run() {
            Date date = new Date();
            Integer year = date.getYear();
            completableFuture.complete(year);//执行complete方法时，get就可以立马返回
        }
    }
}
