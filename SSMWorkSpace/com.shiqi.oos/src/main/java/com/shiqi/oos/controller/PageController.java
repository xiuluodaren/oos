package com.shiqi.oos.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shiqi.oos.controller.base.BaseController;

/**
 * 页面controller
 * @ClassName PageController
 * @Description 
 * @Author 修罗
 * @Date 2018年1月28日 下午1:36:48
 */
@Controller
@RequestMapping("/page")
public class PageController extends BaseController {

	/**
	 * 页面方法
	 * @param page
	 * @param page1
	 * @param page2
	 * @return
	 */
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
			return page;
	}
	@RequestMapping("/{page}/{page1}")
	public String page(@PathVariable String page,@PathVariable String page1) {
			return page + "/" + page1;
	}
	@RequestMapping("/{page}/{page1}/{page2}")
	public String page(@PathVariable String page,@PathVariable String page1,@PathVariable String page2) {
			return page + "/" + page1 + "/" + page2;
	}
	
}
