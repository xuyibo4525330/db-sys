package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysRole;
import com.jt.sys.service.SysRoleService;

@Controller
@RequestMapping("/role/")
public class SysRoleController {
	@Autowired
	private SysRoleService sysRoleService;
	@RequestMapping("doRoleListUI")
	public String doRoleListUI(){
		return "sys/role_list";
	}
	@RequestMapping("doRoleEditUI")
	public String doRoleEditUI(){
		return "sys/role_edit";
	}
	
	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindObjects(){
	 return new JsonResult(sysRoleService.findObjects());
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(Integer id){
		return new JsonResult(
		sysRoleService.findObjectById(id));
		//{data:SysRoleMenuResult}
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			SysRole entity,
			Integer[] menuIds){//{id:1,name:"A",menuIds:"1,2,3,4"}
		sysRoleService.updateObject(entity,
				menuIds);
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysRole entity,
			Integer[] menuIds){//{id:1,name:"A",menuIds:"1,2,3,4"}
		   sysRoleService.saveObject(entity,
				menuIds);
		return new JsonResult("save ok");
	}
	
	
	@RequestMapping("doDeleteObject")
	@ResponseBody
	public JsonResult doDeleteObject(Integer id){
		sysRoleService.deleteObject(id);
		return new JsonResult("delete ok");
	}
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String name,Integer pageCurrent){
		return new JsonResult(sysRoleService
				.findPageObjects(name, 
						pageCurrent));
	}
	
}




