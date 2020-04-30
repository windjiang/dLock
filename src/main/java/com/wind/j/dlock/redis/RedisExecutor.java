package com.wind.j.dlock.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;

import com.wind.j.dlock.service.ExecutorBase;

import lombok.Setter;

/**
 * 
 * @Title: redis execute
 * @author:jiangpeng
 * @date:2020年4月13日 下午8:54:48
 */
public class RedisExecutor implements ExecutorBase {

	@Setter
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public boolean lock(String key, String value, long timeout) {
		// redis执行setnx方法
		return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
	}

	@Override
	public void unlock(String key) {
		redisTemplate.delete(key);
	}

}
