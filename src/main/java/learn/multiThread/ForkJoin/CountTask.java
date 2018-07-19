package learn.multiThread.ForkJoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long> {

    private  final int THRESHOLD = 10000;

    private long start;

    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;
        boolean canCompute = (end - start) < THRESHOLD;
        if (canCompute) {
            for (long i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            long step = (start + end) / 100;
            List<CountTask> subTasks = new ArrayList<CountTask>();

            long pos = start;
            for (int i = 0; i < 100 ; i++) {
                long last = pos + step;
                last = last > end ? end : last;
                CountTask subTask = new CountTask(pos,last);
                pos += step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask t : subTasks) {
                sum += t.join();
            }

        }
        return  sum;
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        CountTask task = new CountTask(0,200000L);
        ForkJoinTask<Long> result = pool.submit(task);

        try {
            long res = result.get();
            System.out.println(res);
        } catch (Exception e) {

        }
    }
}
