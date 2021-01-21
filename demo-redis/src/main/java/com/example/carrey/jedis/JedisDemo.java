package com.example.carrey.jedis;


import redis.clients.jedis.Jedis;

/**
 * @author Conway
 * @className JedisDemo
 * @description
 * @date 2021/1/12 下午3:57
 */
public class JedisDemo {
    public static void main(String[] args) {
        Jedis jedis = null;
        try {
            jedis = new Jedis("172.16.20.5", 6379);
            String hello = jedis.get("hello");
            System.out.println(hello);
            // 1.string
            // 输出结果：OK
            jedis.set("hello", "world");
            // 输出结果：world
            jedis.get("hello");
            // 输出结果：1
            jedis.incr("counter");
            // 2.hash
            jedis.hset("myhash", "f1", "v1");
            jedis.hset("myhash", "f2", "v2");
            // 输出结果：{f1=v1, f2=v2}
            jedis.hgetAll("myhash");
            // 3.list
            jedis.rpush("mylist", "1");
            jedis.rpush("mylist", "2");
            jedis.rpush("mylist", "3");
            // 输出结果：[1, 2, 3]
            jedis.lrange("mylist", 0, -1);
            // 4.set
            jedis.sadd("myset", "a");
            jedis.sadd("myset", "b");
            jedis.sadd("myset", "a");
            // 输出结果：[b, a]
            jedis.smembers("myset");
            // 5.zset
            jedis.zadd("myzset", 99, "tom");
            jedis.zadd("myzset", 66, "peter");
            jedis.zadd("myzset", 33, "james");
            // 输出结果：[[["james"],33.0], [["peter"],66.0], [["tom"],99.0]]
            jedis.zrangeWithScores("myzset", 0, -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }


    }
}
