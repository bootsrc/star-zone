package org.flylib.mall.shop.constant;

import org.flylib.mall.common.constant.UserProfileConstant;
import org.flylib.mall.shop.util.IdUtil;

public class DefaultUserProfile {
    public static final int STAR_SIGN = 0;
    public static final int AGE = 18;
    public static final int SEX = UserProfileConstant.SEX_FEMALE;
    public static final String HEAD_IMG = "/star-sign-file/mobile/default-img/default-mobile-head.png";
    public static final long HEAD_VERSION = 0;
    public static final String NICKNAME_PREFIX = "星客";

    public static String getDefaultNickname(long userId) {
        String tail = userId > 0 ? IdUtil.id2TailStr(userId) : "";
        return NICKNAME_PREFIX + tail;
    }
}
