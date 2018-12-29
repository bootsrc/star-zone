package org.flylib.mall.common.util;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * @author liushaoming
 * @date 2018-04-16 00:06:00
 */
public class CommonObjectConverter {
    public static <T1, T2> T2 convert(T1 src, Class<T2> clazz, Set<String> exceptGetterSet) {
        Class srcClazz = src.getClass();
        T2 target = null;
        try {
            target = clazz.getDeclaredConstructor().newInstance();
            for (Method method : clazz.getMethods()) {
                String setMethodName = method.getName();
                if (setMethodName.startsWith("set") && method.getParameterTypes().length == 1
                        && Modifier.isPublic(method.getModifiers())) {
                    try {
                        String getMethodName = "";
                        if (boolean.class.equals(method.getParameterTypes()[0])) {
                            getMethodName = "is" + setMethodName.substring(3);
                        } else {
                            getMethodName = "get" + setMethodName.substring(3);
                        }
                        if (exceptGetterSet == null || !exceptGetterSet.contains(getMethodName)) {
                            Method srcSetMethod = srcClazz.getMethod(getMethodName);
                            Object fieldValue = srcSetMethod.invoke(src);
                            method.invoke(target, fieldValue);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }
}
