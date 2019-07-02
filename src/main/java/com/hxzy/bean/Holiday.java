package com.hxzy.bean;

import java.util.Date;

public class Holiday {
	private Date from;
	private Date to;
	private String detail;
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}

	@Override
	public String toString() {
		return "Holiday{" +
				"from=" + from +
				", to=" + to +
				", detail='" + detail + '\'' +
				'}';
	}
}
