package learn.zookeeper.curator.book;

import learn.zookeeper.curator.MyCuratorWatcher;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class Watching1 {

    public static void main(String[] args) throws Exception {

        String path = "/create/one";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2182")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        client.start();

        client.getData().usingWatcher(new MyCuratorWatcher()).forPath(path);
        System.in.read();
    }
}
