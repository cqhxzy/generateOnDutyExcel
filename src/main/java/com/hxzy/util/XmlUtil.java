package com.hxzy.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.io.InputStreamReader;

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


}
