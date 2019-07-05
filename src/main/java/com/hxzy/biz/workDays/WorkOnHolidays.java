package com.hxzy.biz.workDays;


public class WorkOnHolidays extends HolidaysTransfer {
    private WorkOnHolidays(){
        super("/workOnHolidays");
    }
    private static WorkOnHolidays instance = new WorkOnHolidays();

    public static WorkOnHolidays getInstance() {
        return instance;
    }

}
