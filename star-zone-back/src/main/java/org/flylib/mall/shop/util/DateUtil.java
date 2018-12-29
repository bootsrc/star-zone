package org.flylib.mall.shop.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    public static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:dd:mm.ms");

    public static final long ONE_DAY_IN_TIME_MILIS = 1000 * 3600 * 24;
    public static final long THREE_MONTHS = 90 * ONE_DAY_IN_TIME_MILIS;

    public static String getDateString(Date date) {
        if (date == null) {
            return "";
        }
        try {
            return format.format(date).trim();
        } catch (Exception e) {
            return "";
        }
    }

//	public static void main(String args[]) {
//		Date date = new Date();
//		System.out.println(date.toString());
//		String timeStr = getDateString(date);
//		System.out.println("-------" + timeStr);
//	}

    public static Date addByMinute(Date date, int amount) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, amount);
        return calendar.getTime();
    }
}
