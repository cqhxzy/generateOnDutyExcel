package com.hxzy.biz.workDays;


public class LegalHoliday extends HolidaysTransfer {

    private LegalHoliday(){
        super("/holidays");
    }
    private static LegalHoliday instance = new LegalHoliday();

    public static LegalHoliday getInstance() {
        return instance;
    }
}
