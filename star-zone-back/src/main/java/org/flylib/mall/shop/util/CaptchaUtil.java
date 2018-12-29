package org.flylib.mall.shop.util;

import java.util.Random;

public class CaptchaUtil {
    public static String newCaptcha() {
        return newCaptcha(6);
    }

    public static String newCaptcha(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }

    private static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }

//    public static void main(String[] args) {
//        for (int i=0; i< 10; i++){
//            String result = newCaptcha(6);
//            System.out.println(result);
//        }
//    }
}
