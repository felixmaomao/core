package com.felix.core.date;/**
 * Created by shenwei on 2017/6/21.
 */

import java.util.Calendar;
import java.util.Date;

/**
 * @author shenwei
 * @create 2017-06-21
 */
public class DateMinutes {
    public static void main(String[] args) {
        System.out.println(DateUtil.preDate(59).toString());
    }

    /**
     * 返回昨天
     *
     * @param today
     * @return
     */
    public static Date yesterday(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
        return calendar.getTime();
    }

    /**
     * @Author shenwei
     * @Date 2017/10/10 14:34
     * @Description 明天
     */
    public static Date tomorrow(Date today) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + 1);
        return calendar.getTime();
    }

    /**
     * @Author shenwei
     * @Date 2017/10/10 14:34
     * @Description 一小时以前
     */
    public static Date anHourAgo() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, c.get(Calendar.HOUR_OF_DAY) - 1);
        return c.getTime();
    }
}
