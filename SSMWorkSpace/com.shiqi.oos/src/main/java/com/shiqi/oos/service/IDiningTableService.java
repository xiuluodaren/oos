package com.shiqi.oos.service;

import java.util.List;
import java.util.Map;

import com.shiqi.oos.entity.SqDiningtable;

/**
 * @ClassName IDiningTableService
 * @Description 
 * @Author 修罗
 * @Date 2018年2月7日 上午11:56:27
 */
public interface IDiningTableService {

	/**
	 * 查询全部餐桌
	 * @return
	 */
	List<SqDiningtable> findAll();
	
	/**
	 * 查询全部可用餐桌
	 * @return
	 */
	List<SqDiningtable> findEnable();

	/**
	 * 查询全部(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqDiningtable> findEasyUI(int page, int rows);

	/**
	 * 查询餐桌(分页)
	 * @param searchKey
	 * @param status
	 * @param page
	 * @param rows
	 * @return
	 */
	List<SqDiningtable> search(String searchKey,String status,int page,int rows);

	/**
	 * 根据ids删除餐桌
	 * @param ids
	 * @return
	 */
	boolean deleteByIds(String ids);

	/**
	 * 根据id（桌号）修改餐桌
	 * @param id
	 * @param renshu
	 * @param status
	 * @return
	 */
	boolean editById(String id, String renshu, String status);

	/**
	 * 添加餐桌
	 * @param zhuohao
	 * @param renshu
	 * @param status
	 * @return
	 */
	boolean add(String zhuohao, String renshu, String status);

}

