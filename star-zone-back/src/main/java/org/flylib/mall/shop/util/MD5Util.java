package org.flylib.mall.shop.util;

import org.springframework.util.DigestUtils;

/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月27日 上午10:16:40 
* 类说明 
*/
public class MD5Util {
	public static String md5(String src) {
		return DigestUtils.md5DigestAsHex(src.getBytes());
	}
}
