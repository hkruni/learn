package learn.zookeeper.curator.book;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class PathChildrenCache_Sample {

    static String path = "/zk-book";
    static CuratorFramework client = getClient();
	public static void main(String[] args) throws Exception {
		PathChildrenCache cache = new PathChildrenCache(client, path, true);
		cache.start(StartMode.POST_INITIALIZED_EVENT);
		cache.getListenable().addListener(new PathChildrenCacheListener() {
			public void childEvent(CuratorFramework client, 
					               PathChildrenCacheEvent event) throws Exception {
				switch (event.getType()) {
				case CHILD_ADDED:
					System.out.println("CHILD_ADDED," + event.getData().getPath());
					break;
				case CHILD_UPDATED:
					System.out.println("CHILD_UPDATED," + event.getData().getPath());
					byte[] data = client.getData().forPath(event.getData().getPath());
					System.out.println("更新后的值为 :" + new String(data));
					break;
				case CHILD_REMOVED:
					System.out.println("CHILD_REMOVED," + event.getData().getPath());
					break;
				default:
					break;
				}
			}
		});
		client.create().withMode(CreateMode.PERSISTENT).forPath(path);
		//Thread.sleep( 1000 );
		//client.create().withMode(CreateMode.PERSISTENT).forPath(path+"/c1");
		//Thread.sleep( 1000 );
		//client.delete().forPath(path+"/c1");
		//Thread.sleep( 1000 );
		//client.delete().forPath(path);
		Thread.sleep(Integer.MAX_VALUE);
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