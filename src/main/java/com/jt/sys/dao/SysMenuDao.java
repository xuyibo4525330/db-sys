package com.jt.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jt.common.vo.Node;
import com.jt.sys.entity.SysMenu;
/**
 * 菜单的持久层对象
 * @author ta
 */
public interface SysMenuDao {
	/**
	 * 基于菜单id获取权限标识
	 * @param menuIds
	 * @return
	 */
	List<String> findPermissions(
			@Param("menuIds") Integer... menuIds);
	/**
	 * 更新菜单信息
	 * @param entity
	 * @return
	 */
	int updateObject(SysMenu entity);
	/**
	 * 写入菜单信息
	 * @param entity
	 * @return
	 */
	int insertObject(SysMenu entity);
	
    /**
     * 查询zTree呈现菜单数据时
     * 需要的菜单节点信息
     * @return
     */
	List<Node> findZtreeMenuNodes();
	
	/**统计此菜单对应的子菜单的个数
	 * @param id 菜单id
	 * @return 菜单的个数
	 */
	int getChildCount(Integer id);
	/**
	 * 基于菜单id执行菜单的删除操作
	 * @param id
	 * @return
	 */
	int deleteObject(Integer id);
	
	
	/**
	 * 查询所有菜单以及菜单对应的上级菜单信息
	 * 思考：
	 * 1)为什么使用map封装每行数据？(map中的key自己定义，灵活性比较)
	 * 2)除了使用map还可以如何封装？(借助VO对象，例如SysMenuResult)
	 * 扩展：了解阿里数据层数据的封装？
	 * @return
	 */
	List<Map<String,Object>> findObjects();
}





