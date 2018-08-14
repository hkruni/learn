package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class GetData {

    public static void main(String[] args) throws Exception {

        String path = "/create/one";
        CuratorFramework client = getClient();

        Stat stat = new Stat();
        byte[] data =  client.getData().storingStatIn(stat).forPath(path);
        System.out.println("节点的数据为 : " + new String(data));
        System.out.println(stat.getAversion());//ACL Version
        System.out.println(stat.getCversion());//Children Version
        System.out.println(stat.getVersion());//Data Version

        client.close();
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
