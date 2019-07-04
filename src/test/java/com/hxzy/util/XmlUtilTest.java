package com.hxzy.util;

import com.hxzy.bean.DutyItem;
import org.dom4j.Document;
import org.dom4j.Element;
import org.junit.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class XmlUtilTest {

    @Test
    public void getDocument() {
        Document document = XmlUtil.getDocument();
        Element root = document.getRootElement();
        List<Element> elements = root.elements();
        List<DutyItem> dutyItems = elements.stream().map(t -> {
            String date = t.element("date").getText();
            List<Element> list = t.elements("list");
            DutyItem item = new DutyItem();
            @SuppressWarnings("unchecked")
            Optional<List<String>> first = list.stream().map(Element::elements).map(k ->
                    (List<String>) k.stream().
                            map(h ->
                                    ((Element) h).getText()
                            ).collect(Collectors.toList())
            ).findFirst();
            item.setDutyDate(date);
            item.setNames(first.orElse(null));
            return item;
        }).collect(Collectors.toList());
        dutyItems.forEach(System.out::println);
    }
}