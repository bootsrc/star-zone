package org.flylib.passport.dao;

import org.flylib.mall.shop.entity.User;
import org.flylib.passport.entity.Token;

/**
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月26日 下午11:14:41 
* 类说明 
*/
public interface TokenDAO {
	String getToken(long userId);
	int add(Token token);
	int updateToken(long userId, String token);
}
