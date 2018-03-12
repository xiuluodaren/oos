package com.shiqi.oos.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shiqi.oos.entity.SqDiningtable;
import com.shiqi.oos.entity.SqDiningtableExample;
import com.shiqi.oos.entity.SqDiningtableExample.Criteria;
import com.shiqi.oos.mapper.SqDiningtableMapper;
import com.shiqi.oos.service.IDiningTableService;
import com.shiqi.oos.utils.EasyUIResult;

/**
 * @ClassName DiningTableServiceImpl
 * @Description 
 * @Author 修罗
 * @Date 2018年2月7日 上午11:57:31
 */
@Service
public class DiningTableServiceImpl implements IDiningTableService {

	@Autowired
	private SqDiningtableMapper diningtableMapper;
	
	/**
	 * 查询全部餐桌
	 * @return
	 */
	@Override
	public List<SqDiningtable> findAll() {
		SqDiningtableExample example = new SqDiningtableExample();
		
		List<SqDiningtable> list = diningtableMapper.selectByExample(example );
		
		return list;
	}

	/**
	 * 查询全部可用餐桌
	 * @return
	 */
	@Override
	public List<SqDiningtable> findEnable() {
		SqDiningtableExample example = new SqDiningtableExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusEqualTo("0");
		
	
		List<SqDiningtable> list = diningtableMapper.selectByExample(example );
		
		return list;
	}

	/**
	 * 查询全部(分页)
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<SqDiningtable> findEasyUI(int page, int rows) {
		
		SqDiningtableExample example = new SqDiningtableExample();
		
		PageHelper.startPage(page, rows);
		List<SqDiningtable> list = diningtableMapper.selectByExample(example );
		
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
	@Override
	public List<SqDiningtable> search(String searchKey,String status,int page,int rows) {
		
		SqDiningtableExample example = new SqDiningtableExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdLike(searchKey + "%");
		
		if (StringUtils.isNotBlank(status)) {
			criteria.andStatusEqualTo(status);
		}
		
		PageHelper.startPage(page, rows);
		List<SqDiningtable> list = diningtableMapper.selectByExample(example );
		
		return list;
	}


	/**
	 * 根据ids删除餐桌
	 * @param ids
	 * @return
	 */
	@Override
	public boolean deleteByIds(String ids) {
	
		String[] idArray = ids.split(",");
		
		for (String id : idArray) {
			if (diningtableMapper.deleteByPrimaryKey(id) <= 0)
			{
				return false;
			}
		}
		
		return true;
	}

	/**
	 * 根据id（桌号）修改餐桌
	 * @param id
	 * @param renshu
	 * @param status
	 * @return
	 */
	@Override
	public boolean editById(String id, String renshu, String status) {
		
		SqDiningtable diningtable = diningtableMapper.selectByPrimaryKey(id);
		
		if (diningtable == null) {
			return false;
		}
		
		diningtable.setRenshu(renshu);
		diningtable.setStatus(status);
		
		if (diningtableMapper.updateByPrimaryKey(diningtable) > 0) {
			return true;
		}else {
			return false;
		}
		
	}

	/**
	 * 添加餐桌
	 * @param zhuohao
	 * @param renshu
	 * @param status
	 * @return
	 */
	@Override
	public boolean add(String zhuohao, String renshu, String status) {
		
		SqDiningtable diningtable = new SqDiningtable();
		diningtable.setId(zhuohao);
		diningtable.setRenshu(renshu);
		diningtable.setStatus(status);
		
		try {
			if (diningtableMapper.insert(diningtable) > 0) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
		
	}
	
}

