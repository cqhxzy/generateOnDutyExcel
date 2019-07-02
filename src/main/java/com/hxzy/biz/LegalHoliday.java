package com.hxzy.biz;


public class LegalHoliday extends HolidaysTransfer {

    private LegalHoliday(){
        super("/holidays.properties");
    }
    private static LegalHoliday instance = new LegalHoliday();

    public static LegalHoliday getInstance() {
        return instance;
    }
}
