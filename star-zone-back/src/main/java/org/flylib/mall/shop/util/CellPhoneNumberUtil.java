package org.flylib.mall.shop.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CellPhoneNumberUtil {
    private static final Logger log = LoggerFactory.getLogger(CellPhoneNumberUtil.class);

    public static String hide(String cellPhone) {
        if (validate(cellPhone)) {
            String middle = cellPhone.substring(3, 9);
            String leftPart = cellPhone.substring(0, 3);
            String rightPart = cellPhone.substring(9);
            String result = leftPart + "******" + rightPart;
            return result;
        } else {
            return cellPhone;
        }

    }

    public static boolean validate(String cellPhone) {
        return (!StringUtils.isBlank(cellPhone)) && cellPhone.length() == 11;
    }
}
