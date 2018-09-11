package com.sy.springboot.demo2.springbootdemo2.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @description: 时间格式化工具类
 * @author: sheny
 * @create: 2018-09-06 16:00
 **/
public class DateFormatUtil {

    public static String toString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String toString(long date, String format) {
        return toString(new Date(date), format);
    }

    public static Date toDate(String date, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(date);
    }
}
