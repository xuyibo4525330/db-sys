package com.jt.common.vo;

import java.io.Serializable;
/**
 * 用于封装数据的节点对象
 * 1)sys_menus (存储的是菜单信息)
 * 2)sys_role_menus(存储的是菜单和角色的关系数据)
 */
public class Node implements Serializable{
	private static final long serialVersionUID = 4351174414771192644L;
	/**节点id(例如菜单id)*/
	private Integer id;
	/**节点名(例如菜单名称)*/
	private String name;
	/**父节点id(例如菜单的上级菜单id)*/
	private Integer parentId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	 
	 
}
