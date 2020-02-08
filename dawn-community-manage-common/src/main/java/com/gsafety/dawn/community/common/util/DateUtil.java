package com.gsafety.dawn.community.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @create 2020-02-07 23:04
 */
public class DateUtil {
    static {}

    /**
     * 获取当前格式化时间字符串yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getNowDateString()
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return  df.format(new Date());
    }

    public static Timestamp convertNowDate(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String dateNow = df.format(calendar.getTime());
        Timestamp ts = Timestamp.valueOf(dateNow);
        return ts;
    }
}
