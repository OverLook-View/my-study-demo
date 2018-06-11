package com.sy.study.springboot.demo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

@Repository
public class RedisService {
    @Autowired
    private JedisPool jedisPool;

    public void setRedis(String key, String value, int secondsTime) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.setex(key, secondsTime, value);
        System.out.println(s);
        jedis.close();
    }

    public String getRedis(String key) {
        Jedis jedis = jedisPool.getResource();
        String s = jedis.get(key);
        jedis.close();
        return s;
    }

    public Set<String> getAllRedis(){
        Jedis jedis = jedisPool.getResource();
        Set<String> set = jedis.keys("*");
        jedis.close();
        return set;
    }
}
