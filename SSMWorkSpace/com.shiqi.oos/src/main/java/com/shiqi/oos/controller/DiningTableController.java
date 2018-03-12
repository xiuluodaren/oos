package com.shiqi.oos.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.pagehelper.Page;
import com.shiqi.oos.entity.SqDiningtable;
import com.shiqi.oos.service.IDiningTableService;
import com.shiqi.oos.utils.AjaxReturn;
import com.shiqi.oos.utils.EasyUIResult;

/**餐桌controller
 * @ClassName DiningTableController
 * @Description 
 * @Author 修罗
 * @Date 2018年2月7日 上午11:49:51
 */
@Controller
@RequestMapping("/dining")
public class DiningTableController {

	@Autowired
	private IDiningTableService diningTableService;
	
	/**
	 * 查询全部餐桌(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/findEasyUI")
	@ResponseBody
	public Map<String, Object> findEasyUI(int page,int rows) {
		Page<SqDiningtable> list = (Page<SqDiningtable>)diningTableService.findEasyUI(page,rows);
		return EasyUIResult.result(list,list.getTotal());
	}
	
	/**
	 * 查询全部可用餐桌
	 * @return
	 */
	@RequestMapping("/findEnablel")
	@ResponseBody
	public List<SqDiningtable> findEnableDiningtable() {
		
		List<SqDiningtable> list = diningTableService.findEnable();
		
		return list;
	}
	
	/**
	 * 查询餐桌(分页)
	 * @param searchKey
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	@ResponseBody
	public Map<String, Object> search(String searchKey,String status,int page,int rows) {
		Page<SqDiningtable> list = (Page<SqDiningtable>)diningTableService.search(searchKey,status,page,rows);
		return EasyUIResult.result(list,list.getTotal());
	}

	/**
	 * 根据ids删除餐桌
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteByIds")
	@ResponseBody
	public Map<String, String> deleteByIds(String ids) {
		
		boolean success = diningTableService.deleteByIds(ids);
		
		if (success) {
			return AjaxReturn.ok("删除成功");
		}else {
			return AjaxReturn.failed("删除失败请重试");
		}
		
	}
	
	/**
	 * 根据id（桌号）修改餐桌
	 * @param id
	 * @param renshu
	 * @param status
	 * @return
	 */
	@RequestMapping("/editById")
	@ResponseBody
	public Map<String, String> editById(String zhuohao,String renshu,String status) {
		if (diningTableService.editById(zhuohao,renshu,status))
		{
			return AjaxReturn.ok("修改成功");
		}else {
			return AjaxReturn.failed("修改失败");
		}
	}
	
	/**
	 * 添加餐桌
	 * @param zhuohao
	 * @param renshu
	 * @param status
	 * @return
	 */
	@RequestMapping("/addDiningTable")
	@ResponseBody
	public Map<String, String> add(String zhuohao,String renshu,String status) {
		
		if (diningTableService.add(zhuohao,renshu,status))
		{
			return AjaxReturn.ok("添加成功");
		}else {
			return AjaxReturn.failed("添加失败");
		}
		
	}
	
}

