package com.hxzy.biz;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        Stream.generate(() -> {
            calendar.add(Calendar.DATE, 1);
            Date date = new Date(calendar.getTimeInMillis());
            return date;
        }).limit(5).forEach(System.out::println);


    }
}
