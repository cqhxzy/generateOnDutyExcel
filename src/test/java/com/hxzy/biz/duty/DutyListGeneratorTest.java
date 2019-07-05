package com.hxzy.biz.duty;

import com.hxzy.bean.DutyItem;
import com.hxzy.bean.DutyList;
import com.hxzy.util.StringUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class DutyListGeneratorTest {

    @Test
    public void getDutyList() {
        List<DutyList> dutyList = DutyListGenerator.getDutyList("2019-08");

        dutyList.forEach(t -> {
            Date date = t.getDate();
            DutyItem item = t.getItem();

            String formatDate = StringUtil.formatDate(date);
            List<String> names = item.getNames();
            String toString = Arrays.toString(names.toArray());

            System.out.println("值班人员：" + toString + ",值班日期：" + formatDate);
        });
    }
}