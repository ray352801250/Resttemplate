package com.dodoca.jedis;

import java.util.Set;

public interface JedisClient {

	public String set(String key, String value);
	public String get(String key);
	public Boolean exists(String key);
	public Long expire(String key, int seconds);
	public Long ttl(String key);
	public Long incr(String key);
	public Long hset(String key, String field, String value);
	public String hget(String key, String field);
	public Long hdel(String key, String... field);
	public Set<String> keys(String key);
	public Set<String> hKeys(String key);
}
