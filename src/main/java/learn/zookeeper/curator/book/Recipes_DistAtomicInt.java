package learn.zookeeper.curator.book;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

// 使用Curator实现分布式计数器
public class Recipes_DistAtomicInt {

	static String distatomicint_path = "/curator_recipes_distatomicint_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("127.0.0.1:2182")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
	public static void main( String[] args ) throws Exception {
		client.start();
		DistributedAtomicInteger atomicInteger = 
		new DistributedAtomicInteger( client, distatomicint_path, 
									new RetryNTimes( 3, 1000 ) );
		atomicInteger.forceSet(0);//初始化赋值为0
		AtomicValue<Integer> rc = atomicInteger.add( 8 );
		rc = atomicInteger.compareAndSet(8,10);//如果当前值为8，就设置当前值为10
		System.out.println( "succeeded: " + rc.succeeded());
		System.out.println( "getStats: " + rc.getStats());
		System.out.println( "更改后的值: " + rc.postValue());
		System.out.println( "原值: " + rc.preValue());
	}
}