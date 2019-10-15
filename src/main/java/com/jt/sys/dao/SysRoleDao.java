package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.CheckBox;
import com.jt.sys.entity.SysRole;
import com.jt.sys.vo.SysRoleMenuResult;

public interface SysRoleDao {
	 /**
	  * 获取所有的角色id，name
	  * @return
	  */
	 List<CheckBox> findObjects();
	 /**
	  * 基于角色id获取角色以及角色对应的菜单信息
	  * @param id 角色id
	  * @return 角色和角色对应的关系数据
	  */
	 //SysRole findObjectById(Integer id);
	  
	  SysRoleMenuResult findObjectById(
			  Integer id);
	  
	  /**
	   * 更新角色信息
	   * @param entity
	   * @return
	   */
	  int updateObject(SysRole entity);
	  
	  
	  
	  /**
	   * 保存角色信息
	   * @param entity
	   * @return
	   */
	  int insertObject(SysRole entity);
	  
	  /**
	   * 基于ID执行删除操作
	   * @param id 角色id
	   * @return 删除的记录行数
	   */
	  int deleteObject(Integer id);
	  
	  
	  
      /**
       * 分页查询用户角色信息
       * @param name 查询条件：角色名
       * @param startIndex 当前起始位置
       * @param pageSize 页面大小
       * @return
       */
	  List<SysRole> findPageObjects(
			     @Param("name")String name,
			     @Param("startIndex")Integer startIndex,
			     @Param("pageSize")Integer pageSize);
	  /**
	   * 依据条件统计记录总数
	   * @param name
	   * @return
	   */
	  int getRowCount(@Param("name")String name);
	  
	  
	  
}





