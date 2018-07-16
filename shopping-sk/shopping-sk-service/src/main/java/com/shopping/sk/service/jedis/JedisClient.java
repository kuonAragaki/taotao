package com.shopping.sk.service.jedis;

public interface JedisClient {
    String get(String key);
    String set(String key, String value);
    String hget(String hkey, String key);
    long hset(String hkey, String key, String value);
    long incr(String key);
    long decr(String key);
    Boolean exists(String key);
    long expire(String key, int second);
    long ttl(String key);
    long del(String key);
    long hdel(String hkey, String key);
    void watch(String key);
    Long lpush(String key,String value);
}
