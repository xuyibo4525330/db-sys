package com.jt.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.CheckBox;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysRoleDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;
import com.jt.sys.vo.SysRoleMenuResult;
@Transactional(isolation=Isolation.READ_COMMITTED)
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
	private SysRoleDao sysRoleDao;
    @Autowired
    private SysRoleMenuDao sysRoleMenuDao;
    @Autowired
    private SysUserRoleDao sysUserRoleDao;
    
    @Override
    public List<CheckBox> findObjects() {
     	return sysRoleDao.findObjects();
    }

    
    /*
    @Override
    public Map<String, Object> findObjectById(Integer id) {
    	//1.参数有效性验证
    	if(id==null||id<1)
    	throw new IllegalArgumentException("id值无效");
    	//2.查询角色自身信息
    	SysRole entity=sysRoleDao.findObjectById(id);
    	if(entity==null)
    	throw new ServiceException("此记录可能已经不存在");
    	//3.查询角色对应的菜单id
    	List<Integer> menuIds=sysRoleMenuDao.findMenuIdsByRoleId(id);
    	//4.封装结果
    	Map<String,Object> map=new HashMap<>();
    	map.put("role", entity);
    	map.put("menuIds", menuIds);
    	return map;
    }
    */
   
    @Override
    public SysRoleMenuResult findObjectById(Integer id) {
    	//1.参数有效性验证
    	if(id==null||id<1)
    	throw new IllegalArgumentException("id值无效");
    	//2.查询角色自身信息
    	SysRoleMenuResult result=
    	sysRoleDao.findObjectById(id);
    	if(result==null)
    	throw new ServiceException("此记录可能已经不存在");
    	System.out.println("role="+result.getRole());
    	return result;
    }
    @Transactional
    @Override
    public int updateObject(SysRole entity, Integer[] menuIds) {
    	//1.参数有效性验证
    	if(entity==null)
    		throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    		throw new IllegalArgumentException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    		throw new ServiceException("必须为角色分配一个菜单权限");
    	//2.保存角色自身信息
    	int rows=sysRoleDao.updateObject(entity);
    	//3.更新角色和菜单的关系数据
    	//3.1删除关系数据
    	sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
    	//3.2保存新的关系数据
    	sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
    	return rows;
    }
    @Override
    public int saveObject(SysRole entity, Integer[] menuIds) {
    	//1.参数有效性验证
    	if(entity==null)
    	throw new IllegalArgumentException("保存对象不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new IllegalArgumentException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    	throw new ServiceException("必须为角色分配一个菜单权限");
    	//2.保存角色自身信息
    	int rows=sysRoleDao.insertObject(entity);
    	//3.保存角色和菜单的关系数据
    	sysRoleMenuDao.insertObjects(
    			      entity.getId(),//来自角色自身信息的写入操作
    			      menuIds);//menuIds来自页面用户的选择
    	return rows;
    }
    
    @Override
    public int deleteObject(Integer id) {
    	//1.参数合法性校验
    	if(id==null||id<1)
    	throw new IllegalArgumentException("id值无效");
    	//2.删除角色自身信息
    	int rows=sysRoleDao.deleteObject(id);
    	if(rows==0)
    	throw new ServiceException("此角色可能已经不存在");
    	//3.删除角色菜单关系数据
    	sysRoleMenuDao.deleteObjectsByRoleId(id);
    	//4.删除角色用户关系数据
    	sysUserRoleDao.deleteObjectsByRoleId(id);
    	//5.返回结果
    	return rows;
    }
    
	@Override
	public PageObject<SysRole> findPageObjects(
			String name, Integer pageCurrent) {
		//1.验证参数合法性
		if(pageCurrent==null||pageCurrent<1)
	    throw new IllegalArgumentException("页码值不正确");
		//2.查询总记录数，并进行验证
		int rowCount=sysRoleDao.getRowCount(name);
		if(rowCount==0)
		throw new ServiceException("没有对应记录");
		//3.查询当前页的角色信息
		int pageSize=2;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRole> records=
		sysRoleDao.findPageObjects(name,
				startIndex, pageSize);
		//4.封装结果并返回
		PageObject<SysRole> po=new PageObject<>();
		po.setRecords(records);
		po.setRowCount(rowCount);
		po.setPageSize(pageSize);
		po.setPageCurrent(pageCurrent);
		//总页数的计算提取到PageObject对应的getPageCount方法中
		//po.setPageCount((rowCount-1)/pageSize+1);
		return po;
	}

}









