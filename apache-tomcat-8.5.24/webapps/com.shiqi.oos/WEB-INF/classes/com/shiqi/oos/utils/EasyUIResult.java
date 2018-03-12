package com.shiqi.oos.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EasyUIResult {

	static String TOTAL = "total";
	static String ROWS = "rows";
	
	public static Map<String, Object>	result(List<?> rows) {
		
		if (rows == null) {
			rows = new ArrayList<>();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put(TOTAL, rows.size());
		map.put(ROWS, rows);
		
		return map;
		
	}
	
	public static Map<String, Object>	result(List<?> rows,long total) {
		
		if (rows == null) {
			rows = new ArrayList<>();
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put(TOTAL, total);
		map.put(ROWS, rows);
		
		return map;
		
	}
	
}
