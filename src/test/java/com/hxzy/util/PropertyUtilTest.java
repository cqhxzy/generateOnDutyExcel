package com.hxzy.util;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PropertyUtilTest {

    @Test
    public void getValue() {
        String index = PropertyUtil.getValue("index");
        System.out.println(index);
    }

    @Test
    public void saveObj() {
        PropertyUtil.saveObj("index","10");
    }
}