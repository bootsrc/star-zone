package org.flylib.passport.service;

import org.flylib.passport.entity.Passport;
import org.flylib.passport.redis.CommonCacheKey;
import org.flylib.passport.redis.RedisClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月27日 下午8:08:19 
* 类说明 
*/
@Service
public class TokenRedisService {
	private static final Logger logger = LoggerFactory.getLogger(TokenRedisService.class);
	
	@Autowired
	private RedisClient redisClient;
	
	public String getToken(Long userId) {
		String key = CommonCacheKey.PASSPORT_PREFIX + userId;
		String tokenInRedis = redisClient.get(key);
		logger.info("TokenRedisService.getToken:key={},value={}", new Object[] { key, tokenInRedis });
		return tokenInRedis;
	}
	
	public String setToken(Long userId, String token) {
		String key = CommonCacheKey.PASSPORT_PREFIX + userId;
		String result = redisClient.set(key, token);
		logger.info("TokenRedisService.setToken:key={},value={}", new Object[] { key, token });
		return result;
	}

	public void test(){
		String key = "mytest";
		String value = "test-value1";
		redisClient.set(key, value);
		String resultValue = redisClient.get(key);
		logger.info("--redis_test_result,key={}, value={}", key, resultValue);
	}
}
