package com.shiqi.oos.controller.base;

import org.apache.log4j.Logger;

/**
 * BaseController
 * @author xiuluo
 *
 */
public class BaseController {

	/**
	 * 成功
	 */
	public final static String SUCCESS = "true";
	
	/**
	 * 失败
	 */
	public final static String FAIL = "false";
	
	protected Logger logger = Logger.getLogger(this.getClass());
	
}
