package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class CreateNode {

    public static void main(String[] args) throws Exception {

        CuratorFramework client = getClient();
        client.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/create/one","good".getBytes());

    }

    private static CuratorFramework getClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("112.35.29.127:2181")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();
        client.start();
        return client;
    }
}
