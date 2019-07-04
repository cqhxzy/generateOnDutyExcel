package com.hxzy.util;

import com.hxzy.bean.DutyItem;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class XmlUtil {
    private static final String DEFAULT_PATH = "/dutyList.xml";

    public static Document getDocument() {
        SAXReader reader = new SAXReader();
        try {
            InputStream resourceAsStream = XmlUtil.class.getResourceAsStream(DEFAULT_PATH);
            InputStreamReader isr = new InputStreamReader(resourceAsStream);
            return reader.read(isr);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<DutyItem> getDutyItems(){
        Document document = getDocument();
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
        return dutyItems;
    }
}
