package com.jt.sys.service;
import java.util.List;

import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysRole;
import com.jt.sys.vo.SysRoleMenuResult;

public interface SysRoleService {
    public List<CheckBox> findObjects();

	/**
	 * 基于角色id查询角色以及角色与菜单的关系数据
	 * @param id
	 * @return
	 */
	/***
	Map<String,Object> findObjectById(
			Integer id);
			*/
	SysRoleMenuResult findObjectById(Integer id);
	
	/**
	 * 更新角色以及角色与菜单的关系数据
	 */
	int updateObject(SysRole entity,
			Integer[] menuIds);
	
	/**
	 * 保存角色以及角色与菜单的关系数据
	 */
	int saveObject(SysRole entity,
			        Integer[] menuIds);
	
	/**
	 * 基于角色id删除角色信息以及关系数据
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
     /**
      * 分页查询当前的角色记录信息
      * @param name 角色名
      * @param pageCurrent 当前页码值
      * @return 当前页记录以及分页信息
      */
	 PageObject<SysRole> findPageObjects(
			 String name,
			 Integer pageCurrent);
}






