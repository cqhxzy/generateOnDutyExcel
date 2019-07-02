package com.hxzy.biz;

import com.hxzy.bean.Holiday;
import com.hxzy.util.PropertyUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HolidaysTransfer {
    protected List<Holiday> holidays;
    private String path;

    public String getPath() {
        return path;
    }

    public HolidaysTransfer(String path) {
        this.path = path;
    }

    public List<Holiday> getHoliday() {
        if (holidays == null) {
            setHoliday();
        }
        return holidays;
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
    protected void setHoliday() {
        Collection<Object> holidays_str = PropertyUtil.getValues(this.path);
        holidays = holidays_str.stream().map((t)->{
            String str = (String)t;
            String[] split = str.split("\\s+");
            Holiday holiday = new Holiday();
            for (int i = 0; i < split.length; i++) {
                String k = split[i];
                if (k.indexOf("from") != -1) {
                    String from = k.substring(k.indexOf(":") + 1);
                    holiday.setFrom(convertStr2TimeMills(from));
                }
                if (k.indexOf("to") != -1) {
                    String to = k.substring(k.indexOf(":") + 1);

                    /*long l = convertStr2TimeMills(to);
                    Calendar instance = Calendar.getInstance();
                    instance.setTimeInMillis(l); //假日截止日的第二天开始上班
                    instance.add(Calendar.DATE,1);*/
                    holiday.setTo(convertStr2TimeMills(to));
                }
                if (k.indexOf("detail") != -1) {
                    String detail = k.substring(k.indexOf(":") + 1);
                    holiday.setDetail(detail);
                }
            }
            return holiday;
        }).collect(Collectors.toList());
    }
}
