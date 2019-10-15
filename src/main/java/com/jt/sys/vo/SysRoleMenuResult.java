package com.jt.sys.vo;
import java.util.List;
import com.jt.sys.entity.SysRole;
/**
 * VO：通过此对象封装角色以及角色对应的菜单id
 * @author ta
 */
public class SysRoleMenuResult {
	/**角色对象*/
	private SysRole role;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
	public SysRole getRole() {
		return role;
	}
	public void setRole(SysRole role) {
		this.role = role;
	}
	public List<Integer> getMenuIds() {
		return menuIds;
	}
	public void setMenuIds(List<Integer> menuIds) {
		this.menuIds = menuIds;
	}
}
