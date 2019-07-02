package com.hxzy.biz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test2 {
	private static long current = System.currentTimeMillis(); //当前时间

	public static void main(String[] args) {
		List<Date> list = new ArrayList<>();

		Calendar c = Calendar.getInstance();
		c.setTime(converMills2Date(current));
		int actualMaximum = c.getActualMaximum(Calendar.DATE); //每个月的最后一天
		int actualMinimum = c.getActualMinimum(Calendar.DATE); //每个月的第一天

		c.set(Calendar.DATE, actualMinimum);// 每个月的1号为起点

		current = c.getTimeInMillis(); //得到第一天的毫秒
		list.add(converMills2Date(current)); //将毫秒转换为日期对象

		List<Date> list2 = Stream.generate((Supplier<Date>) () -> {
			Date instance = converMills2Date(current);
			c.setTime(instance);
			c.add(Calendar.DATE, 1);
			current = c.getTimeInMillis();
			return c.getTime();
		})
				.limit(actualMaximum - 1)
				.collect(Collectors.toList());

		list.addAll(list2); //合并集合

		list.stream().forEach(t -> {
			System.out.println(t);

		});
	}

	private static Date converMills2Date(long current){
		Date date = new Date(current);
		return date;
	}

}
