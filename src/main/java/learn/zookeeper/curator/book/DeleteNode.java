package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

public class DeleteNode {

    public static void main(String[] args) throws Exception {
        String path = "/create";

        CuratorFramework client = getClient();
        Stat stat = client.checkExists().forPath(path);
        if(stat == null) {
            System.out.println("节点不存在");
        } else {
            client.delete().deletingChildrenIfNeeded().forPath(path);
        }


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
