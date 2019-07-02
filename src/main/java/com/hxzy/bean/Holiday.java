package com.hxzy.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Holiday implements Cloneable{
	private long from;
	private long to;
	private String detail;
	public long getFrom() {
		return from;
	}
	public void setFrom(long from) {
		this.from = from;
	}
	public long getTo() {
		return to;
	}
	public void setTo(long to) {
		this.to = to;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public Holiday clone() throws CloneNotSupportedException {

		Holiday holiday = (Holiday) super.clone();
		return holiday;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateFrom = new Date(from);
		String str_from = sdf.format(dateFrom);
		Date dateTo = new Date(to);
		String str_to = sdf.format(dateTo);
		return "Holiday{" +
				"from=" + str_from +
				", to=" + str_to +
				", detail='" + detail + '\'' +
				'}';
	}
}
