package com.gsafety.dawn.community.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by nusmg 2017-12-18
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
}
