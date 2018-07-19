package learn.multiThread.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ParallelSearch {

    public static int search(int[] values,int searchValue,int start,int end){
        for (int i = start; i < end; i++) {
            if(values[i] == searchValue)
                return i;
        }
        return -1;
    }

    public static class FindValue implements Callable<Integer>{

        private int[] value;
        private int start;
        private int end;
        private int searchValue;

        public FindValue(int [] value,int start ,int end,int searchValue){
            this.value = value;
            this.start = start;
            this.end = end;
            this.searchValue = searchValue;
        }

        @Override
        public Integer call() throws Exception {
            Integer key = search(value,searchValue,start,end);
            return key;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int []values = new int[3000];
        int segmentSiez = 300;
        for (int i = 0 ;i < 3000 ; i++) {
            if(i == 698)
                values[i] = 100;
            else
                values[i] = (int)(Math.random() * 100);
        }
        List<Future<Integer>> list = new ArrayList<Future<Integer>>();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 3000; i += 300) {
            int start = i;
            int end = i + 300;
            list.add(executor.submit(new FindValue(values,start,end,100)));
        }

        for (Future<Integer> fu : list) {
            if(fu.get() >= 0)
                System.out.println(fu.get());
        }
    }

}
