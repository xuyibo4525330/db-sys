package com.jt.sys.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.Node;
import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;

@Service
public class SysMenuServiceImpl implements SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	
	@Override
	public int updateObject(SysMenu entity) {
		//1.验证参数有效性
		if(entity==null)
	    throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
		throw new IllegalArgumentException("菜单名不能为空");
		if(StringUtils.isEmpty(entity.getPermission()))
		throw new IllegalArgumentException("权限标识不能为空");
		//自己扩展：基于用户输入的permission查询数据库是否有重复的
		//2.保存数据
		int rows=sysMenuDao.updateObject(entity);
		if(rows==0)
	    throw new ServiceException("此记录可能已经不存在");
		//3.返回结果
		return rows;
	}
	
	@Override
	public int saveObject(SysMenu entity) {
		//1.验证参数有效性
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getName()))
			throw new IllegalArgumentException("菜单名不能为空");
		if(StringUtils.isEmpty(entity.getPermission()))
			throw new IllegalArgumentException("权限标识不能为空");
		//自己扩展：基于用户输入的permission查询数据库是否有重复的
		//2.保存数据
		int rows=sysMenuDao.insertObject(entity);
		//3.返回结果
		return rows;
	}
	@Override
	public List<Node> findZtreeMenuNodes() {
		return sysMenuDao.findZtreeMenuNodes();
	}
	@Override
	public int deleteObject(Integer id) {
		//1.验证参数有效性
		if(id==null||id<1)
		throw new IllegalArgumentException("参数无效");
		//2.基于id判定是否有子菜单
		int count=sysMenuDao.getChildCount(id);
		if(count>0)
		throw new ServiceException("请先删除子菜单");
		//3.基于id删除菜单表中记录
		int rows=sysMenuDao.deleteObject(id);
		//4.基于id删除菜单角色表中记录
		sysRoleMenuDao.deleteObjectsByMenuId(id);
		//5.返回删除结果
		return rows;
	}
	
	@Override
	public List<Map<String, Object>> findObjects() {
		return sysMenuDao.findObjects();
	}

}
