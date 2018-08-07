package learn.zookeeper.curator.book;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;

import java.util.List;

public class GetChildren {

    public static void main(String[] args) throws Exception {
        String path = "/create";

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("112.35.29.127:2182")
                .sessionTimeoutMs(5000)
                .namespace("base")
                .retryPolicy(new ExponentialBackoffRetry(1000,3))
                .build();

        client.start();
        try {
            List<String> childNodes =  client.getChildren().forPath(path);
            if (childNodes != null && childNodes.size() > 0) {
                for(String s : childNodes) {
                    System.out.println("子节点名称 ： "  + s);
                    List<String> childNodes1 = client.getChildren().forPath(path + "/" + s );
                    if (childNodes1 != null && childNodes1.size() > 0) {
                        for (String s1 : childNodes1) {
                            System.out.println(s1);
                        }
                    }
                }
            }
        }catch (KeeperException.NoNodeException e) {
            e.printStackTrace();
        }



    }
}
