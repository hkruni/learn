package learn.zookeeper.curator.book;
import learn.multiThread.ReentrantLock.Counter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicInteger;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;

import java.util.concurrent.CountDownLatch;

// 使用Curator实现分布式计数器
public class Recipes_DistAtomicInt {

	static String distatomicint_path = "/curator_recipes_distatomicint_path";
    static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("112.35.29.127:2182")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
	public static void main( String[] args ) throws Exception {
		client.start();
		//每隔一秒重试一次，重试30次，如果重试次数太少可能有些线程无法执行
		DistributedAtomicInteger atomicInteger = new DistributedAtomicInteger( client, distatomicint_path,
									new RetryNTimes( 1, 1000 ) );
		atomicInteger.forceSet(0);//初始化赋值为0
		System.out.println(atomicInteger.add( 8 ).postValue());
		atomicInteger.compareAndSet(8,10);//如果当前值为8，就设置当前值为10

		CountDownLatch countDownLatch1 = new CountDownLatch(1);
		CountDownLatch countDownLatch2 = new CountDownLatch(30);

		for (int i = 0; i < 30; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						countDownLatch1.await();
						atomicInteger.increment();
						countDownLatch2.countDown();
					} catch (Exception e) {
						System.out.println(Thread.currentThread().getName() + "出现问题");
						e.printStackTrace();
					}
				}
			}).start();
		}
		countDownLatch1.countDown();
		countDownLatch2.await();
		AtomicValue<Integer> rc = atomicInteger.get();
		System.out.println( "succeeded: " + rc.succeeded());
		System.out.println( "getStats: " + rc.getStats().getPromotedLockTries());
		System.out.println( "更改后的值: " + rc.postValue());
		System.out.println( "原值: " + rc.preValue());
	}
}