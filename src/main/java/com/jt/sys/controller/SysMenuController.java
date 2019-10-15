package com.jt.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysMenu;
import com.jt.sys.service.SysMenuService;

@Controller
@RequestMapping("/menu/")
public class SysMenuController {
	  @Autowired
	  private SysMenuService sysMenuService;
	  
	  /**
	   * 呈现菜单列表页面
	   * @return
	   */
	  @RequestMapping("doMenuListUI")
	  public String doMenuListUI(){
		  return "sys/menu_list";
	  }
	  /**
	   * 加载菜单编辑页面(添加和修改操作
	   * 共用一个页面)
	   * @return
	   */
	  @RequestMapping("doMenuEditUI")
	  public String doMenuEditUI(){
		  return "sys/menu_edit";
	  }
	  
	  @RequestMapping("doUpdateObject")
	  @ResponseBody
	  public JsonResult doUpdateObject(
			  SysMenu entity){
		  System.out.println("log="+entity);
		  sysMenuService.updateObject(entity);
		  return new JsonResult("upate ok");
	  }
	  @RequestMapping("doSaveObject")
	  @ResponseBody
	  public JsonResult doSaveObject(
			  SysMenu entity){
		  System.out.println("log="+entity);
		  sysMenuService.saveObject(entity);
		  return new JsonResult("save ok");
	  }
	  
	  @RequestMapping("doFindZtreeMenuNodes")
	  @ResponseBody
	  public JsonResult doFindZtreeMenuNodes(){
			 return new JsonResult(
		  sysMenuService.findZtreeMenuNodes());
	  }

	  
	  @RequestMapping("doDeleteObject")
	  @ResponseBody
	  public JsonResult doDeleteObject(Integer id){
		  sysMenuService.deleteObject(id);
		  return new JsonResult("delete ok");
	  }
	  /**加载菜单以及上级菜单信息*/
	  @RequestMapping("doFindObjects")
	  @ResponseBody
	  public JsonResult doFindObjects(){
		  return new JsonResult(
		  sysMenuService.findObjects());
	  }
}                                                  






