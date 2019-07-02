package com.hxzy.biz;


import com.hxzy.bean.Holiday;
import com.hxzy.util.PropertyUtil;

import java.util.Collection;
import java.util.stream.Collectors;

public class WorkOnHolidays extends HolidaysTransfer {
    private WorkOnHolidays(){
        super("/workOnHolidays.properties");
    }
    private static WorkOnHolidays instance = new WorkOnHolidays();

    public static WorkOnHolidays getInstance() {
        return instance;
    }

}
