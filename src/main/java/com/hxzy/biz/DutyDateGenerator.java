package com.hxzy.biz;

import com.hxzy.bean.Holiday;
import com.hxzy.util.HolidayUtil;
import com.hxzy.util.WorkOnHolidays;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DutyDateGenerator {
    public static List<Date> getDudyDate(String dateStr) {
        List<Date> list = new ArrayList<>();

        Calendar c = Calendar.getInstance(); //获取当前日期
        Date date = converStr2Date(dateStr);
        c.setTime(date);

        int actualMaximum = c.getActualMaximum(Calendar.DATE); //每个月的最后一天
        int actualMinimum = c.getActualMinimum(Calendar.DATE); //每个月的第一天

        c.set(Calendar.DATE, actualMinimum);// 每个月的1号为值班起点

        list.add(converMills2Date(c.getTimeInMillis())); //将毫秒转换为日期对象

        List<Date> list2 = Stream.generate(() -> {

            c.add(Calendar.DATE, 1);

            return c.getTime();
        })
                .limit(actualMaximum - 1)
                .collect(Collectors.toList());

        list.addAll(list2); //合并集合

        List<Date> collect = list.stream()
                //.filter(t -> isWorkOnHoliday(t)) //排除周末补班的情况
                //.filter(t -> isNotWeekDay(t))  //排除周末
                //.filter(t -> isNotHoliday(t))  //排除法定节日
                .filter(t ->
                        isWorkOnHoliday(t) || isNotWeekDay(t) && isNotHoliday(t)
                )
                .collect(Collectors.toList());
        return collect;
    }

    private static Date converMills2Date(long current) {
        Date date = new Date(current);
        return date;
    }

    private static boolean isNotWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY;
    }

    private static boolean isNotHoliday(Date date) {
        List<Holiday> holiday = HolidayUtil.getInstance().getHoliday();
        return !holiday.stream().anyMatch(t ->
                date.getTime() >= t.getFrom() && date.getTime() <= t.getTo()
        );
    }

    public static boolean isWorkOnHoliday(Date date) {
        List<Holiday> workDay = WorkOnHolidays.getInstance().getHoliday();

        return workDay.stream()
                .filter(t ->
                        {
                            Calendar c1 = Calendar.getInstance();
                            Calendar c2 = Calendar.getInstance();
                            Calendar c3 = Calendar.getInstance();

                            c1.setTime(date);
                            c2.setTimeInMillis(t.getFrom());
                            c3.setTimeInMillis(t.getTo());
                            return c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) || c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH) - 1;
                        }
                )
                .anyMatch(t ->
                        date.getTime() >= t.getFrom() && date.getTime() <= t.getTo()
                );
    }

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
}
