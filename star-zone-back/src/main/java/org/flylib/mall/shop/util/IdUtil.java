package org.flylib.mall.shop.util;

public class IdUtil {
    /**
     * 保留id的最多4位尾数
     * @param id
     * @return
     */
    public static String id2TailStr(long id) {
        String idStr = id+"";
        int len = idStr.length();
        if (len > 4) {
            return idStr.substring(len-4);
        } else {
            return idStr;
        }
    }

    public static void main(String[] args){
        System.out.println(id2TailStr(100));
        System.out.println(id2TailStr(469093324058263552L));

        System.out.println(id2TailStr(4690933240582L));
    }
}
