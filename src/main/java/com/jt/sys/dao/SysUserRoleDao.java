package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 此DAO负责操作用户和角色的关系表。
 * 思考：
 * 1)用户和角色是什么关系？多对多关系(many2many)
 * 2)多对多的关系在表设计时如何实现？(中间表)
 */

public interface SysUserRoleDao {
	/**
	 * 基于用户id获取用户对应的角色id
	 * @param id
	 * @return
	 */
	List<Integer> findRoleIdsByUserId(Integer id);
	
	/**
	 * 负责将用户与角色的关系数据写入到数据库
	 * @param userId 用户id
	 * @param roleIds 多个角色id
	 * @return
	 */
	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer... roleIds);
	
	/**
	 * 基于用户id删除角色和用户的关系数据
	 * @param userId 用户id
	 * @return 删除的行数
	 */
	int deleteObjectsByUserId(Integer userId);
	

    /**
     * 基于角色id删除角色和用户的关系数据
     * @param roleId 角色id
     * @return 删除的行数
     */
	int deleteObjectsByRoleId(Integer roleId);
	
	
    /**
	   * 尝试如何通过如下一个方法替换如上两个方法
	   * @param columnName
	   * @param columnValue
	   * @return
	   */
	 int deleteObjectsById(
			  @Param("columnName")String columnName,
			  @Param("columnValue")Integer columnValue);
	
}
