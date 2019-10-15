package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * 1.角色和菜单是什么关系？M2M
 */

public interface SysRoleMenuDao {
	/**
	 * 基于角色ID查询菜单id
	 * @param roleId
	 * @return 多个菜单id
	 */
	 List<Integer> findMenuIdsByRoleId(Integer roleId);
	 
	 List<Integer> findMenuIdsByRoleIds(
			 @Param("roleIds")
			 Integer... roleIds);
	/**
	 * 保存角色和菜单的关系数据
	 * @param roleId 角色id
	 * @param menudIds 菜单id
	 * @return
	 */
	int insertObjects(
			@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	
	/**
	 * 基于菜单id删除角色和菜单的关系数据
	 * @param menuId
	 * @return
	 */
	int deleteObjectsByMenuId(Integer menuId);
	/**
	 * 基于角色id删除角色和菜单的关系数据
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	
	
}
