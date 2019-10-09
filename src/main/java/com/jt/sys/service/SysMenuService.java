package com.jt.sys.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;

public interface SysMenuService {
	
	/**
	 * 保存菜单信息
	 * @param entity
	 * @return
	 */
	
	int updateObject(SysMenu entity);
	/**
	 * 保存菜单信息
	 * @param entity
	 * @return
	 */
	
	int saveObject(SysMenu entity);
	
	/**
	 * 查询菜单节点信息
	 * @return
	 */
	List<Node> findZtreeMenuNodes();
	
	/**
	 * 执行菜单的删除操作
	 * 1)有子菜单则不允许删除
	 * 2)删除当前菜单信息
	 * 3)删除当前菜单与角色的关系数据
	 * @param id 菜单id
	 * @return 删除的行数
	 */
	int deleteObject(Integer id);
	

	List<Map<String,Object>> findObjects();
}
