package com.hxzy.biz;


import com.hxzy.biz.workDays.DutyDateGenerator;
import com.hxzy.util.StringUtil;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DutyDateGeneratorTest {

    @Test
    public void getDudyDate() {
        List<Date> dudyDate = DutyDateGenerator.getDudyDate("2019-06");
        dudyDate.forEach(t->
            System.out.println(StringUtil.formatDate(t))
        );
    }

    @Test
    public void isWorkOnHoliday() {
        Calendar c = Calendar.getInstance();
        c.set(2019,Calendar.AUGUST,3);

        boolean workOnHoliday = DutyDateGenerator.isWorkOnHoliday(c.getTime());
        System.out.println(workOnHoliday);
    }
}