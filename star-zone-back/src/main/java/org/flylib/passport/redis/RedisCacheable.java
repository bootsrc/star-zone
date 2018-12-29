package org.flylib.passport.redis;

import java.io.Serializable;

/**
 * 标识接口 声明实现此接口的bean，客户通过 MobileUserDataCacheJedisPoolImpl 类 doSave，get方法缓存到redis服务器
 * 
 * @author Frank Liu(liushaomingdev@163.com)
 * 
 */
public interface RedisCacheable extends Serializable
{
    
}
