package org.flylib.passport.service;

import org.flylib.passport.dao.TokenDAO;
import org.flylib.passport.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月27日 上午11:00:37 
* 类说明 
*/
@Service
public class TokenService {
	@Autowired
	private TokenDAO tokenDAO;
	
	@Autowired
	private TokenRedisService tokenRedisService;
	
	public String getToken(long userId) {
		String cachedToken = tokenRedisService.getToken(userId);
		String token = "";
		if (StringUtils.isEmpty(cachedToken)) {
			token = tokenDAO.getToken(userId);
		} else {
			token = cachedToken;
		}
		return token;
	}
	
	public String copyTokenToCache(long userId) {
		String tokenInDB = tokenDAO.getToken(userId);
		tokenRedisService.setToken(userId, tokenInDB);
		return tokenInDB;
	}
	
	public Integer insert(long userId, String token, long expire) {
		Token tokenObj = new Token();
		tokenObj.setUserId(userId);
		Date nowTime = new Date();
		tokenObj.setCreateTime(nowTime);
		tokenObj.setUpdateTime(nowTime);
		tokenObj.setExpireTime(new Date(nowTime.getTime() + expire));
		tokenObj.setToken(token);
		Integer count = tokenDAO.add(tokenObj);
		if (count > 0) {
			tokenRedisService.setToken(userId, token);
		}
		return count;
	}
	
	public Integer updateToken(long userId, String token) {
		Integer count = tokenDAO.updateToken(userId, token);
		if (count > 0) {
			tokenRedisService.setToken(userId, token);
		}
		return count;
	}
}
