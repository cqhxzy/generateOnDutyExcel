package com.hxzy.util;

import com.hxzy.bean.Holiday;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class PropertyUtil {

	private static Collection<Object> holidays;
	private static List<Holiday> holiday;
	
	static{
		Properties prop = new Properties();
		try {
			InputStream inputStream = PropertyUtil.class.getResourceAsStream("/holidays.properties");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			prop.load(inputStreamReader);
			holidays = prop.values();
			//holidays.stream().forEach(System.out::println);
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
		if (holiday == null) {
			setHoliday();
		}
		return holiday;
	}
	private static void setHoliday() {
		holidays.stream().forEach(t -> {
			String str = (String)t;
			String[] split = str.split("\\s+");
			System.out.println(Arrays.toString(split));
			holiday = Arrays.stream(split).map((k) -> {
				Holiday holiday = new Holiday();
				if (k.indexOf("from") != -1) {
					String substring = k.substring(6);
					holiday.setFrom(convertStr2Date(substring));
				}
				if (k.indexOf("to") != -1) {
					String substring = k.substring(k.indexOf("to") + 3);
					holiday.setTo(convertStr2Date(substring));
				}
				if (k.indexOf("detail") != -1) {
					String substring = k.substring(k.indexOf("detail") + 7);
					holiday.setDetail(substring);
				}
				return holiday;
			}).collect(Collectors.toList());
			/*Arrays.stream(split).forEach(j->{
				if (j.indexOf("from") != -1) {
					String substring = j.substring(6);
					holiday.setFrom(convertStr2Date(substring));
				}
				if (j.indexOf("to") != -1) {
					String substring = j.substring(j.indexOf("to") + 3);
					holiday.setTo(convertStr2Date(substring));
				}
				if (j.indexOf("detail") != -1) {
					String substring = j.substring(j.indexOf("detail") + 7);
					holiday.setDetail(substring);
				}
			});
			holidays.add(holiday);*/
		});
	}
	
	private static Date convertStr2Date(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
