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

        CuratorFramework client = getClient();

        final NodeCache nodeCache = new NodeCache(client,path,false);//false表示不压缩
        //true表示nodeCache第一次启动时就会立刻从ZK上读取节点数据并进行本地缓存
        nodeCache.start(true);
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                if(nodeCache.getCurrentData() != null) {//创建节点或修改节点数据
                    System.out.println("节点是 " + nodeCache.getCurrentData().getPath());
                    System.out.println("节点数据更新，更新后数据为 " + new String(nodeCache.getCurrentData().getData()));
                }else {//删除节点
                    System.out.println("cache don't set a value");
                }
            }
        });

        System.in.read();


    }

    private static CuratorFramework getClient() {
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("112.35.29.127:2182")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        client.start();
        return client;
    }


}
