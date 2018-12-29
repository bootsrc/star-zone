package org.flylib.passport.service;

import org.flylib.passport.entity.Passport;
import org.flylib.passport.redis.CommonCacheKey;
import org.flylib.passport.redis.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月20日 下午2:01:52 
* 类说明 
*/
@Component
public class LoginIntercepterService {
	@Autowired
	private TokenService tokenService;
	
	public Passport getPassport(Long userId) {
		String token = tokenService.getToken(new Long(userId));
		Passport passport = new Passport();
		passport.setUserId(userId);
		passport.setToken(token);
		return passport;
	}
}
