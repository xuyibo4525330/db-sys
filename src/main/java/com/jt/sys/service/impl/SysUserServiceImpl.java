package com.jt.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.common.exception.ServiceException;
import com.jt.common.vo.PageObject;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
import com.jt.sys.vo.SysUserDeptResult;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	@Override
	public Map<String, Object> findObjectById(
				Integer userId) {
			//1.合法性验证
			if(userId==null||userId<=0)
			throw new ServiceException(
			"参数数据不合法,userId="+userId);
			//2.业务查询
			SysUserDeptResult user=
			sysUserDao.findObjectById(userId);
			if(user==null)
			throw new ServiceException("此用户已经不存在");
			List<Integer> roleIds=
			sysUserRoleDao.findRoleIdsByUserId(userId);
			//3.数据封装
			Map<String,Object> map=new HashMap<>();
			map.put("user", user);
			map.put("roleIds", roleIds);
			return map;
	  }

	@Override
	public int updateObject(SysUser entity,
			Integer... roleIds) {
		//1.参数有效性验证
		if(entity==null)
		throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
		throw new IllegalArgumentException("用户名不能为空");
		//...
		//2.保存用户自身信息
	    int rows=sysUserDao.updateObject(entity);
		//3.保存用户和角色的关系数据
	    //3.1删除用户角色关系数据
	    //sysUserRoleDao.deleteObjectsByUserId(entity.getId());
	    sysUserRoleDao.deleteObjectsById(
	    		"user_id",
	    		entity.getId());
	    //3.2重写写入用户角色关系数据
	    sysUserRoleDao.insertObjects(entity.getId(),
	    		roleIds);
		//4.返回结果
		return rows;
	}
	@Override
	public int saveObject(SysUser entity,
			Integer... roleIds) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new IllegalArgumentException("密码不能为空");
		//...
		//2.保存用户自身信息
		//2.1产生随机字符串作为一个盐值
		String salt=UUID.randomUUID().toString();
		entity.setSalt(salt);
		//2.2对密码进行加密(借助shiro中api)
		//DigestUtils.md5DigestAsHex(bytes)//spring中自带API
		SimpleHash sHash=new SimpleHash(//Shiro中API
				"md5",//algorithmName为加密算法
				entity.getPassword(), //source 密码
				salt);//salt 盐值
		entity.setPassword(sHash.toHex());
		//2.3持久化用户自身信息
		int rows=sysUserDao.insertObject(entity);
		//3.保存用户和角色的关系数据
		sysUserRoleDao.insertObjects(entity.getId(),
				roleIds);
		//4.返回结果
		return rows;
	}
	//user-->admin-->{"sys:user:valid","sys:user:update"}
	//此注解由shiro定义
	//借助此注解定义访问此方法需要什么权限
	@RequiresPermissions("sys:user:valid")
	//当方法上有如上注解时系统底层会为业务对象产生一个代理对象
	//并要对此代理对象进行生命周期管理。
	//当执行此方法时，系统底层会通过代理对象，
	//调用subject.isPermitted("sys:user:valid")来进行权限检测
	//此时subject会将权限系统提交给SecuityManager对象
	//SecuityManager会将权限信息交给授权管理器
	//授权管理器会调用realm方法查询用户权限
	@Override
	public int validById(Integer id, 
			Integer valid, 
			String modifiedUser) {
		//1.参数有效性校验
		if(id==null||id<1)
		throw new IllegalArgumentException("id值无效");
		if(valid==null||(valid!=1&&valid!=0))
		throw new IllegalArgumentException("valid值不正确");
		if(StringUtils.isEmpty(modifiedUser))
		throw new IllegalArgumentException("修改用户不能为空");
		//2.禁用启用操作
		int rows=sysUserDao.validById(id, valid, modifiedUser);
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		//3.返回结果
		return rows;
	}
	
    @Override
	public PageObject<SysUserDeptResult> findPageObjects(String username, 
			Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
		throw new ServiceException("参数不合法");
        //2.依据条件获取总记录数
		int rowCount=sysUserDao.getRowCount(username);
        if(rowCount==0)
		throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<SysUserDeptResult> records=
			sysUserDao.findPageObjects(
		username, startIndex, pageSize);
		//5.封装数据
		PageObject<SysUserDeptResult> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		//pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}


}
