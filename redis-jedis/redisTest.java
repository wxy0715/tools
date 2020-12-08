package com.hotel.utils.redis;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author wxy
 * @description
 * @data 2020/4/26
 */
public class redisTest {
    public static void main(String[] args) {
        Jedis jedis  = new Jedis("localhost",6379);
        System.out.println(jedis.ping());

        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));

        //存储数据到列表中
        jedis.lpush("test","RunOob");
        jedis.lpush("test","Google");
        jedis.lpush("test","TaoBao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("test", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }

        //取出所有的key
        Set<String> keys = jedis.keys("*");
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println("key==="+key);
        }
    }
}
