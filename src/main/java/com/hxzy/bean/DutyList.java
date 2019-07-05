package com.hxzy.bean;

import java.util.Date;

public class DutyList {
    private Date date;
    private DutyItem item;

    public DutyList(Date date, DutyItem item) {
        this.date = date;
        this.item = item;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public DutyItem getItem() {
        return item;
    }

    public void setItem(DutyItem item) {
        this.item = item;
    }
}
