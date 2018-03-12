package com.shiqi.oos.utils;

import java.util.HashMap;
import java.util.Map;

public class AjaxReturn {

	static String SUCCESS = "success";
	static String MESSAGE = "message";
	static String TRUE = "true";
	static String FALSE = "false";
	
	String message;
	
	boolean success;
	
	public AjaxReturn(boolean success,String message) {
		this.message = message;
		this.success = success;
	}
	
	public static Map<String, String> ok(String message) {
		
		Map<String, String> map = new HashMap<String,String>();
		
		map.put(SUCCESS, TRUE);
		map.put(MESSAGE, message);
		
		return map;
	}
	
	public static Map<String, String> failed(String message) {
		
		Map<String, String> map = new HashMap<String,String>();
		
		map.put(SUCCESS, FALSE);
		map.put(MESSAGE, message);
		
		return map;
	}
	
}
