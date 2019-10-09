package com.jt.sys.service.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jt.sys.dao.SysMenuDao;
import com.jt.sys.dao.SysRoleMenuDao;
import com.jt.sys.dao.SysUserDao;
import com.jt.sys.dao.SysUserRoleDao;
import com.jt.sys.entity.SysUser;
/**
 * 此对象中要完成用户认证信息，授权信息
 * 的获取和封装等业务操作。
 */
@Service
public class ShiroUserRealm extends AuthorizingRealm {
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysMenuDao sysMenuDao;
	
	/**
	 * 指定加密算法和加密次数(默认就是1次)
	 * @param credentialsMatcher
	 */
    @Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		// TODO Auto-generated method stub
		HashedCredentialsMatcher hashMatcher=
		new HashedCredentialsMatcher("MD5");
		//hashMatcher.setHashIterations(1024);
		super.setCredentialsMatcher(hashMatcher);
	}
	/**
	 * 负责用户认证信息的获取以及封装
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		System.out.println("doGetAuthenticationInfo");
		//1.获取用户身份信息(例如用户名)
		String userName=(String)token.getPrincipal();//身份(控制层提交)
		//2.基于用户名访问数据库获取用户信息
		SysUser user=sysUserDao.findUserByUserName(userName);
		//3.对用户信息进行验证
		//3.1验证是否为空(为空说明此此用户不存在)
		if(user==null)
		throw new UnknownAccountException();
		//3.2验证此用户是否被禁用了(禁用则不允许登录)
		if(user.getValid()==0)
		throw new LockedAccountException();
		//4.基于业务封装用户数据?(例如密码，盐值)
		ByteSource credentialsSalt=
		ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
			user, //principal (身份)
			user.getPassword(),//hashedCredentials(已加密的密码)
			credentialsSalt, //credentialsSalt
			this.getName());//realmName(当前类的名字)
		System.out.println("realmName="+this.getName());
		return info;//将此值返回给认证管理器(Authentication)
	}
	/**负责用户授权信息({"sys:user:valid",...})
	 * 的获取及封装*/
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("doGetAuthorizationInfo");
		//1.获取用户id(基于此id逐步获取用户具备的权限)
		SysUser user=(SysUser)principals.getPrimaryPrincipal();
		//2.基于用户id获取角色信息(用户角色中间表：user_id,role_id)
		List<Integer> roleIds=sysUserRoleDao.findRoleIdsByUserId(user.getId());
		//3.基于角色id获取菜单id(角色菜单关系表：role_id,menu_id)
		List<Integer> menuIds=
		sysRoleMenuDao.findMenuIdsByRoleIds(
		roleIds.toArray(new Integer[]{}));
		//4.基于菜单id获取权限标识(例如"sys:user:valid")
		List<String> permissions=
		sysMenuDao.findPermissions(
		menuIds.toArray(new Integer[]{}));
		//5.封装权限信息
		SimpleAuthorizationInfo info=
		new SimpleAuthorizationInfo();
		Set<String> perSet=new HashSet<>();
		for(String per:permissions){
			if(!StringUtils.isEmpty(per)){
				perSet.add(per);
			}
		}
		System.out.println("user.permissions="+perSet);
		info.setStringPermissions(perSet);
		return info;//此对象会传递给谁？授权管理器
	}
}
