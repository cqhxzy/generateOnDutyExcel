package com.hxzy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class StringUtil {
    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E");
        String format = sdf.format(date);
        return format;
    }

    public static Date converStr2Date(String string) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date parse = sdf.parse(string);
            return parse;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date formatStr2DateMonth(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date parse = null;
        try {
            parse = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 验证日期是否合法
     * @param str
     * @return
     */
    public static void validateDateStr(String str){
        String regex = "2[\\d]{3}-([1-9]|0[\\d]|1[0-2])";
        if (!str.matches(regex))
            throw new RuntimeException("输入的日期格式不正确。请输入yyyy-MM格式的日期");
    }
    public static long convertStr2TimeMills(String str){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String converMills2DateStr(long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(time);
        return sdf.format(date);
    }
    public static boolean isEmpty(Object obj){
        return obj == null;
    }
}
