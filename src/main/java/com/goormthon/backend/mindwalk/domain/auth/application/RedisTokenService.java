package com.goormthon.backend.mindwalk.domain.auth.application;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTokenService {

	private final StringRedisTemplate redisTemplate;

	public RedisTokenService(StringRedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public void saveRefreshToken(Long userId, String refreshToken, long duration, TimeUnit timeUnit) {
		String key = "refresh:" + userId;
		redisTemplate.opsForValue()
			.set(key, refreshToken, duration, timeUnit);
	}

	public String getRefreshToken(Long userId) {
		String key = "refresh:" + userId;
		return redisTemplate.opsForValue().get(key);
	}

	public void deleteRefreshToken(Long userId) {
		String key = "refresh:" + userId;
		redisTemplate.delete(key);
	}
}
