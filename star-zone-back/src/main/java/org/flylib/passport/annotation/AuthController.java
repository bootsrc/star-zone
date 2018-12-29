package org.flylib.passport.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Inherited
//@Target(ElementType.METHOD)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/** 
* @author Frank Liu(liushaomingdev@163.com)
* @version 创建时间：2017年8月21日 下午10:40:49 
* 类说明 
* Controller需要Passport认证
*/
public @interface AuthController {
//	boolean validate() default true;
	boolean value() default true;
}
