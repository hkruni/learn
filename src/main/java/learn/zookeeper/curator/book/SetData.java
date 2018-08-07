package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class SetData {

    public static void main(String[] args) throws Exception {
        String path = "/create/";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("112.35.29.127:2182")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        client.start();
        client.create().creatingParentsIfNeeded().forPath(path);
        client.setData().forPath(path,"two".getBytes());
    }



}
