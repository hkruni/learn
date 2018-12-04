package learn.redis;

import redis.clients.jedis.Jedis;

import javax.swing.text.BadLocationException;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lock {

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";
    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {

        int x  = 0;
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 50; i++) {
            es.submit(new Runnable() {
                @Override
                public void run() {
                    Jedis jedis = JedisUtil.getJedis();
                    String uuid = UUID.randomUUID().toString();
                    boolean isSuccess = Lock.tryGetDistributedLock(jedis,"lock",uuid,3000);
                    if (isSuccess) {
                        jedis.incr("num");
//                        try {
//                            Thread.sleep(1000);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                        Lock.releaseDistributedLock(jedis,"lock",uuid);
                    }
                    jedis.close();

                }
            });
        }
        es.shutdown();


    }


}
