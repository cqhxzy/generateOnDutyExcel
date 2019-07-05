package com.hxzy.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import org.apache.poi.ss.usermodel.CellStyle;


public class DutyList extends BaseRowModel {
    /**
     * value: 表头名称
     * index: 列的号, 0表示第一列
     */
    @ExcelProperty(value = "值班人员", index = 0)
    private String item1;

    @ExcelProperty(value = "值班日期",index = 1)
    private String date1;

    @ExcelProperty(value = "值班人员", index = 2)
    private String item2;

    @ExcelProperty(value = "值班日期",index = 3)
    private String date2;

    public DutyList(String item1, String date1, String item2, String date2) {
        this.item1 = item1;
        this.date1 = date1;
        this.item2 = item2;
        this.date2 = date2;
    }

    public String getItem1() {
        return item1;
    }

    public String getDate1() {
        return date1;
    }

    public String getItem2() {
        return item2;
    }

    public String getDate2() {
        return date2;
    }

    @Override
    public String toString() {
        return "DutyList{" +
                "item1='" + item1 + '\'' +
                ", date1='" + date1 + '\'' +
                ", item2='" + item2 + '\'' +
                ", date2='" + date2 + '\'' +
                '}';
    }
}
