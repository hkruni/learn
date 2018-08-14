package learn.zookeeper.curator.application;

import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * Created by hukai on 2018/8/14.
 */
public class DistributedID {

    private static String PATH = "/id_path/ids-";

    public static void main(String[] args) throws Exception {

        CuratorFramework client = getClient();
        String str = client.create().creatingParentsIfNeeded()
                .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                .forPath(PATH);//创建顺序节点

        String s = StringUtils.substring(str,str.lastIndexOf("/") + 1);
        System.out.println(s);



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
