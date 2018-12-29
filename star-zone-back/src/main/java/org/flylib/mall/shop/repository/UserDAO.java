package org.flylib.mall.shop.repository;

import org.flylib.mall.shop.entity.User;

/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月26日 下午8:45:39 
* 类说明 
*/
public interface UserDAO {
	int add(User user);
	User getUserByMobile(String mobile);
	int updatePassword(long userId, String password);
	User findById(long id);
}
