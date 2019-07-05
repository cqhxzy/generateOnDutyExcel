package com.hxzy;

import com.hxzy.bean.DutyList;
import com.hxzy.biz.duty.DutyListGenerator;
import com.hxzy.excel.ExcelUtil;
import com.hxzy.util.StringUtil;

import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("*************************************");;
        System.out.println("*********华信智原值班生成器**********");
        System.out.println("*************************************");
        System.out.println("\n");
        System.out.println("请输入生成值班表日期。日期格式：YYYY-MM");
        String dateStr = input.next();

        StringUtil.validateDateStr(dateStr);
        List<DutyList> dutyList = DutyListGenerator.getDutyList(dateStr);

        String path = "C://华信智原" + StringUtil.formatDate2YearMonth(dateStr) + "夜间值班表.xlsx";
        ExcelUtil.writeWithTemplate(path ,dutyList);
        System.out.println("生成成功，请至C盘查看文件");
    }
}
