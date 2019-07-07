package com.hxzy.util;

import java.io.*;
import java.util.*;

public class PropertyUtil {
	public static final String KEY_INDEX = "index";
	private static final Properties prop = new Properties();
	private static final String PATH = PropertyUtil.class.getResource("/config.properties").getPath();
	static{
		try {

			InputStream inputStream = PropertyUtil.class.getResourceAsStream("/config.properties");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			prop.load(inputStreamReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static  String getValue(String key){
		return (String) prop.get(key);
	}

	public static void saveObj(String key, String  value){
		prop.setProperty(key, value);
		save("update" + key);
	}

	private static void save(String comments){
		try {
			prop.store(new FileWriter(PATH),comments);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
