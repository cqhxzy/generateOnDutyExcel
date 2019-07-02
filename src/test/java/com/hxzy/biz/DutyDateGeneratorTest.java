package com.hxzy.biz;


import org.junit.Test;

import java.util.Date;
import java.util.List;

public class DutyDateGeneratorTest {

    @Test
    public void getDudyDate() {
        List<Date> dudyDate = DutyDateGenerator.getDudyDate("2019-8-1");
        dudyDate.forEach(t->
            System.out.println(DutyDateGenerator.formatDate(t))
        );
    }
}