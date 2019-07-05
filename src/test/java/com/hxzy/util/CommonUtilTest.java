package com.hxzy.util;

import org.junit.Test;

import java.io.*;
import java.util.Collection;

import static org.junit.Assert.*;

public class CommonUtilTest {

    @Test
    public void getValues() {
        Collection<String> values = CommonUtil.getValues("/workOnHolidays");
        System.out.println(values.size());

    }
}