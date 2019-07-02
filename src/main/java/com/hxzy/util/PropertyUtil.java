package com.hxzy.util;

import java.io.*;
import java.util.*;

public class PropertyUtil {

	public static  Collection getValues(String path){
		Properties prop = new Properties();
		try {
			InputStream inputStream = PropertyUtil.class.getResourceAsStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			prop.load(inputStreamReader);
			return prop.values();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
