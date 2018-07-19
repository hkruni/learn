package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

public class GetChildren {

    public static void main(String[] args) throws Exception {
        String path = "/create";

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2182")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        client.start();

        List<String> childNodes =  client.getChildren().forPath(path);
        for(String s : childNodes) {
            System.out.println(s);
        }
    }
}
