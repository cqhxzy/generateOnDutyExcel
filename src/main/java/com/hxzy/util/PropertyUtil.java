package com.hxzy.util;

import com.hxzy.bean.Holiday;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PropertyUtil {

	private static Collection<Object> holidays_str;
	private static List<Holiday> holidays;
	
	static{
		Properties prop = new Properties();
		try {
			InputStream inputStream = PropertyUtil.class.getResourceAsStream("/holidays.properties");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			prop.load(inputStreamReader);
			holidays_str = prop.values();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		List<Holiday> holiday = getHoliday();
		holiday.forEach(System.out::println);
	}
	public static List<Holiday> getHoliday() {
		if (holidays == null) {
			setHoliday();
		}
		return holidays;
	}
	private static void setHoliday() {
		holidays = holidays_str.stream().map((t)->{
			String str = (String)t;
			String[] split = str.split("\\s+");
			Holiday holiday = new Holiday();
			for (int i = 0; i < split.length; i++) {
				String k = split[i];
				if (k.indexOf("from") != -1) {
					String from = k.substring(k.indexOf(":") + 1);
					long l = convertStr2TimeMills(from);
					Calendar instance = Calendar.getInstance();
					instance.setTimeInMillis(l); //假日截止日的第二天开始上班
					instance.add(Calendar.DATE,1);

					holiday.setFrom(instance.getTimeInMillis());
				}
				if (k.indexOf("to") != -1) {
					String to = k.substring(k.indexOf(":") + 1);
					holiday.setTo(convertStr2TimeMills(to));
				}
				if (k.indexOf("detail") != -1) {
					String detail = k.substring(k.indexOf(":") + 1);
					holiday.setDetail(detail);
				}
			}
			return holiday;
		}).collect(Collectors.toList());
	}
	
	public static long convertStr2TimeMills(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
