package com.hxzy.excel;

import com.hxzy.bean.DutyList;
import com.hxzy.biz.duty.DutyListGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ExcelUtilTest {

    @Test
    public void test1(){
        String filePath = "E://测试.xlsx";
        List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        List<String> head = Arrays.asList("表头1", "表头2", "表头3");
        ExcelUtil.writeBySimple(filePath,data,head);

    }

    @Test
    public void test2(){

    }

    @Test
    public void test3(){
        List<DutyList> dutyList = DutyListGenerator.getDutyList("2019-07");
        dutyList.forEach(System.out::println);
        /*String filePath = "E://值班名单.xlsx";
        ExcelUtil.writeWithTemplate(filePath,dutyList);*/

    }
}