package org.flylib.passport.service;

import org.flylib.passport.dao.TokenDAO;
import org.flylib.passport.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月27日 下午9:04:17 
* 类说明 
*/
@Service
public class TokenDBService {
	@Autowired
	private TokenDAO tokenDAO;
	
	public String getToken(long userId) {
		String token = tokenDAO.getToken(userId);
		return token;
	}
	
	public Integer insert(Token token) {
		Integer count = tokenDAO.add(token);
		return count;
	}
	
	public Integer updateToken(Long userId, String token) {
		Integer count = tokenDAO.updateToken(userId, token);
		return count;
	}
}
