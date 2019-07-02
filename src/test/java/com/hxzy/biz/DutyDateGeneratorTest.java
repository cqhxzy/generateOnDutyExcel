package com.hxzy.biz;


import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DutyDateGeneratorTest {

    @Test
    public void getDudyDate() {
        List<Date> dudyDate = DutyDateGenerator.getDudyDate("2019-7-1");
        dudyDate.forEach(t->
            System.out.println(DutyDateGenerator.formatDate(t))
        );
    }

    @Test
    public void isWorkOnHoliday() {
        Calendar c = Calendar.getInstance();
        c.set(2019,Calendar.JULY,7);

        boolean workOnHoliday = DutyDateGenerator.isWorkOnHoliday(c.getTime());
        System.out.println(workOnHoliday);
    }
}