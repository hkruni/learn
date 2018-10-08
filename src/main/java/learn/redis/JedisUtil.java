package learn.redis;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisUtil {

    private static JedisPool jedisPool = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(300);
        config.setMaxIdle(200);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(config, "localhost", 6379, 10000);
    }

    public static void main(String[] args) {
        Jedis jedis = jedisPool.getResource();
        System.out.println(jedis.setbit("c1",2,true));
        System.out.println(jedis.setbit("c1",4,true));
        System.out.println(jedis.setbit("c1",6,true));
        System.out.println(jedis.setbit("c1",8,true));
        System.out.println(jedis.setbit("c2",1,true));
        System.out.println(jedis.setbit("c2",3,true));
        System.out.println(jedis.setbit("c2",5,true));
        System.out.println(jedis.setbit("c2",7,true));
        jedis.bitop(BitOP.OR,"c3","c1","c2");
        jedis.bitop(BitOP.AND,"c4","c1","c2");
        jedis.bitop(BitOP.XOR,"c5","c1","c2");
        System.out.println(jedis.bitcount("c3"));
        System.out.println(jedis.bitcount("c4"));
        System.out.println(jedis.bitcount("c5"));

    }

}
