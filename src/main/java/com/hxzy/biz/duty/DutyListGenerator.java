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

    public static List<DutyList> getDutyList(String dateStr){
        //获取值班日期
        List<Date> dutyDate = DutyDateGenerator.getDudyDate(dateStr);

        //获取值班人员名单
        List<DutyItem> dutyItems = getDutyItems();

        //获取上一次值班人员的索引
        int index = Integer.parseInt(PropertyUtil.getValue("index"));

        List<DutyList> dutyLists =new ArrayList<>();
        /*for (int i = 0; i < dudyDate.size() ; i++) {
            if (index >= dutyItems.size()) index = 0;
            Date date = dudyDate.get(i);
            DutyItem dutyItem = dutyItems.get(index++);

            DutyList duty = new DutyList(StringUtil.formatDate(date), dutyItem.toString());
            dutyLists.add(duty);
        }*/
        int date2_index = 15;
        int item2_index = 0;

        if (dutyItems.size() > 15){
            item2_index = dutyItems.size() - 15;
        } else {
            item2_index = (15 + index) % dutyItems.size();
        }


        for (int i = 0; i < 15 ; i++) {
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
        PropertyUtil.saveObj(PropertyUtil.KEY_INDEX,String.valueOf(index));
        return dutyLists;
    }

}
