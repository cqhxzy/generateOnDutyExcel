package com.hxzy.util;


public class HolidayUtil extends Utils {

    private HolidayUtil(){
        super("/holidays.properties");
    }
    private static HolidayUtil instance = new HolidayUtil();

    public static HolidayUtil getInstance() {
        return instance;
    }
}
