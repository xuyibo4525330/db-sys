package com.jt.sys.service;

import java.util.Map;

import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysUser;
import com.jt.sys.vo.SysUserDeptResult;

public interface SysUserService {
	
	/**
	 * 基于用户id获取用户自身信息，对应部门信息以及角色id
	 * @param userId
	 * @return
	 */
	Map<String, Object> findObjectById(
				Integer userId);

	
	int validById(Integer id,Integer valid,String modifiedUser);
   
	int saveObject(SysUser entity,Integer... roleIds);
	
	int updateObject(SysUser entity,Integer... roleIds);
	
	PageObject<SysUserDeptResult> findPageObjects(
			String username,
			Integer pageCurrent);
}
