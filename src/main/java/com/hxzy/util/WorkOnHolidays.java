package com.hxzy.util;


import com.hxzy.bean.Holiday;

import java.util.Calendar;
import java.util.Collection;
import java.util.stream.Collectors;

public class WorkOnHolidays extends Utils {
    private WorkOnHolidays(){
        super("/workOnHolidays.properties");
    }
    private static WorkOnHolidays instance = new WorkOnHolidays();

    public static WorkOnHolidays getInstance() {
        return instance;
    }

    protected void setHoliday() {
        Collection<Object> holidays_str = PropertyUtil.getValues(getPath());
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
