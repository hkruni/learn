package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class NodeCache1 {

    public static void main(String[] args) throws Exception {

        String path = "/create/one";

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2182")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        client.start();

        NodeCache nodeCache = new NodeCache(client,path,false);
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                if(nodeCache.getCurrentData() != null) {
                    System.out.println("节点是 " + nodeCache.getCurrentData().getPath());
                    System.out.println("节点数据更新，更新后数据为 " + new String(nodeCache.getCurrentData().getData()));
                }else {
                    System.out.println("cache don't set a value");
                }
            }
        });

        System.in.read();


    }


}
