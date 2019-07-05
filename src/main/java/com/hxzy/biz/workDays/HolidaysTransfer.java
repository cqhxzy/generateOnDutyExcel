package com.hxzy.biz.workDays;

import com.hxzy.bean.Holiday;
import com.hxzy.util.CommonUtil;
import com.hxzy.util.StringUtil;

import java.util.Collection;
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

    protected void setHoliday() {
        Collection<String> holidays_str = CommonUtil.getValues(this.path);
        holidays = holidays_str.stream().map((t)->{
            String[] split = t.split("\\s+");
            Holiday holiday = new Holiday();
            for (int i = 0; i < split.length; i++) {
                String k = split[i];
                if (k.indexOf("from") != -1) {
                    String from = k.substring(k.indexOf(":") + 1);
                    holiday.setFrom(StringUtil.convertStr2TimeMills(from));
                }
                if (k.indexOf("to") != -1) {
                    String to = k.substring(k.indexOf(":") + 1);
                    holiday.setTo(StringUtil.convertStr2TimeMills(to));
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
