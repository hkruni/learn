package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public class GetData {

    public static void main(String[] args) throws Exception {

        String path = "/create/one";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("127.0.0.1:2182")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        client.start();
        Stat stat = new Stat();
        byte[] data =  client.getData().storingStatIn(stat).forPath(path);
        System.out.println("节点的数据为 : " + new String(data));
        System.out.println(stat.getAversion());//ACL Version
        System.out.println(stat.getCversion());//Children Version
        System.out.println(stat.getVersion());//Data Version

        client.close();
    }

}
