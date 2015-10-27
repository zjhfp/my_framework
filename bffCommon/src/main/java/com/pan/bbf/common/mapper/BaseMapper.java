package com.pan.bbf.common.mapper;

import java.util.List;

public interface BaseMapper {

	/**
	 * 新增
	 * @param t
	 * @return
	 */
	public <T> void insert(T t);
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 */
	public <T> T findById(String id);
	
	/**
	 * 查询所有
	 * @return
	 */
	public <T> List<T> findAll();
	
	/**
	 * 根据ID删除
	 * @param id
	 */
	public <T> void deleteById(String id);
	
	/**
	 * 更新
	 * @param t
	 * @return
	 */
	public <T> void update(T t);
	
	public <T> void updateSelective(T t);
}
