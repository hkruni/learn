package learn.multiThread.CountDownLatch;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程读文本文件
 */
public class MultiReadTxt {

    private static List<String> list = Collections.synchronizedList(new LinkedList<String>());


    public static class ReadFile implements Runnable{

        private String filename;

        private CountDownLatch latch;

        public ReadFile(String filename,CountDownLatch latch) {
            this.filename = filename;
            this.latch = latch;
        }

        @Override
        public void run() {
            String result = readToString(filename);
            String []array = result.split(",");
            for (String s :array){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(s);
            }
            latch.countDown();

        }

        public String readToString(String fileName) {
            String encoding = "UTF-8";
            File file = new File(fileName);
            Long filelength = file.length();
            byte[] filecontent = new byte[filelength.intValue()];
            try {
                FileInputStream in = new FileInputStream(file);
                in.read(filecontent);
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return new String(filecontent, encoding);
            } catch (UnsupportedEncodingException e) {
                System.err.println("The OS does not support " + encoding);
                e.printStackTrace();
                return null;
            }
        }

    }


    public static void main(String[] args) throws Exception{
        CountDownLatch latch = new CountDownLatch(4);
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        ReadFile r1 = new ReadFile("D:\\1.txt",latch);
        ReadFile r2 = new ReadFile("D:\\2.txt",latch);
        ReadFile r3 = new ReadFile("D:\\3.txt",latch);
        ReadFile r4 = new ReadFile("D:\\4.txt",latch);

        executorService.submit(r1);
        executorService.submit(r2);
        executorService.submit(r3);
        executorService.submit(r4);

        latch.await();
        for (String s : list) {
            System.out.print(s + ",");
        }

    }

}
