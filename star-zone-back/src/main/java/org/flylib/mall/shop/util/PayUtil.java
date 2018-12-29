package org.flylib.mall.shop.util;

public class PayUtil {
//    public static void main(String[] args) {
//        String text = "少明商家号通过扫码向你付款0.01元";
//        long amount = getAmount(text);
//        System.out.println(amount);
//    }

    public static long getAmount(String text) {
        String[] array = text.split("通过扫码向你付款");
        if (array.length!= 2) {
            return 0;
        } else {
            String tmp = array[1];
            String[] array1 = tmp.split("元");
            if (array1.length == 1) {
                String amountStr = array1[0];
                Float amount = Float.valueOf(amountStr);
                long amountValue = (long) (amount * 100);
                return amountValue;
            } else {
                return 0;
            }
        }
    }
}
