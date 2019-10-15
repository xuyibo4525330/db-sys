package com.jt.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserDao {
	/**
	 * 基于用户名查询用户信息
	 * @param username
	 * @return
	 */
	SysUser findUserByUserName(String username);
	
	int getUserCountByDeptId(Integer deptId);
	/**
	 * 基于用户id查找用户以及用户对应的部门信息
	 * @param id 用户id
	 * @return
	 */
	SysUserDeptResult findObjectById(Integer id);
	
	/**
	 * 负责将用户信息更新到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysUser entity);
	
	/**
	 * 负责将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUser entity);

	/**
	 * 禁用启用用户对象
	 * @param id  用户id
	 * @param valid 禁用启用标识(值取1和0)
	 * @return
	 */
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
    
	/**
	 * 依据条件执行分页查询操作
	 * @param username
	 * @param startIndex
	 * @param pageSize
	 * @return
	 */
	List<SysUserDeptResult> findPageObjects(
			@Param("username") String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/**依据条件统计记录总数*/
	int getRowCount(@Param("username") String username);

}
