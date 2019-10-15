package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysConfig;

public interface SysConfigDao {
	
	int updateObject(SysConfig entity);
	/**
	 * 负责将配置信息持久化
	 * @param entity
	 * @return
	 */
	int insertObject(SysConfig entity);
	
	/**
	 * 基于id执行删除操作
	 * @param ids
	 * @return
	 */
	int deleteObjects(
			@Param("ids")Integer... ids);

    /**
     * 基于条件查询当前页数据
     * @param name 查询时输出的参数名
     * @param startIndex 当前起始位置
     * @param pageSize 每页最多显示的记录数,页面大小.
     * @return
     */
	List<SysConfig> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**
	 * 基于条件查询总记录数
	 * @param name 查询条件
	 * @return
	 */
	int getRowCount(@Param("name")String name);
}







