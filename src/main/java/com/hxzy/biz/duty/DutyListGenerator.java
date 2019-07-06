package com.hxzy.biz.duty;

import com.hxzy.bean.DutyItem;
import com.hxzy.bean.DutyList;
import com.hxzy.biz.workDays.DutyDateGenerator;
import com.hxzy.util.PropertyUtil;
import com.hxzy.util.StringUtil;
import com.hxzy.util.XmlUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.dom4j.Document;
import org.dom4j.Element;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 负责将值班日期以及人员名单中的数据组合成值班名单表
 */
public class DutyListGenerator {

    //生成的EXCEL数据行数
    private static final int EXCEL_ROW = 15;
    
    /**
     * 从XML中解析出值班人员名单
     * @return
     */
    private static List<DutyItem> getDutyItems(){
        Document document = XmlUtil.getDocument();
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        List<DutyItem> dutyItems = elements.stream().map(t -> {
            List<Element> list = t.elements("list");
            DutyItem item = new DutyItem();
            @SuppressWarnings("unchecked")
            Optional<List<String>> first = list.stream().map(Element::elements).map(k ->
                    (List<String>) k.stream().
                            map(h ->
                                    ((Element) h).getText()
                            ).collect(Collectors.toList())
            ).findFirst();
            item.setNames(first.orElse(null));
            return item;
        }).collect(Collectors.toList());
        return dutyItems;
    }

    /**
    * <p>根据字符串日期生成值班日期及人员</p>
    * <p>值班日期生成算法：</p>
    * <p>字符串日期，计算出该月所有的工作日至一个集合中</P>
    * <p>工作日的定义：周末需要补班的，除了周末的及法定节日的均为工作日</p>
    * @params dateStr 字符串格式的日期，格式为：<code>YYYY-MM</code>
    * @return 每个DutyList内封装的是一行值班日期及值班人员信息
    */
    public static List<DutyList> getDutyList(String dateStr){
        //获取值班日期
        List<Date> dutyDate = DutyDateGenerator.getDudyDate(dateStr);

        //获取值班人员名单
        List<DutyItem> dutyItems = getDutyItems();

        //获取上一次值班人员的索引
        int index = Integer.parseInt(PropertyUtil.getValue("index"));

        List<DutyList> dutyLists =new ArrayList<>();
       
        //第二个值班日期列的起始索引
        int date2_index = EXCEL_ROW;
        
        //第二个值班人员列的起始索引
        int item2_index = 0;

        if (dutyItems.size() > EXCEL_ROW){
            item2_index = dutyItems.size() - EXCEL_ROW;
        } else {
            item2_index = (EXCEL_ROW + index) % dutyItems.size();
        }


        for (int i = 0; i < EXCEL_ROW ; i++) {
            if (index >= dutyItems.size()) index = 0;
            Date date = dutyDate.get(i);
            DutyItem dutyItem = dutyItems.get(index++);

            String date1 = StringUtil.formatDate(date);
            String item1 = dutyItem.toString();

            String date2 = "";
            String item2 = "";


            if (date2_index < dutyDate.size()){
                Date duty_date2 = dutyDate.get(date2_index++);
                date2 = StringUtil.formatDate(duty_date2);


                if (item2_index >= dutyItems.size()) {
                    item2_index = 0;
                }
                item2 = dutyItems.get(item2_index++).toString();
            }

            DutyList duty = new DutyList(item1,date1,item2,date2);
            dutyLists.add(duty);
        }

        //将index写回文件
        PropertyUtil.saveObj(PropertyUtil.KEY_INDEX,String.valueOf(item2_index));
        return dutyLists;
    }

}
