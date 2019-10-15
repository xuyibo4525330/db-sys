package com.jt.sys.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.common.vo.PageObject;
import com.jt.sys.entity.SysConfig;
import com.jt.sys.service.SysConfigService;

@Controller
@RequestMapping("/config/")
public class SysConfigController {

	@Autowired
    private SysConfigService sysConfigService;
    
	@RequestMapping("doConfigListUI")
	public String doConfigListUI(){
		return "sys/config_list";
	}
	
	@RequestMapping("doConfigEditUI")
	public String doConfigEditUI(){
		return "sys/config_edit";
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(SysConfig entity){
		sysConfigService.updateObject(entity);
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysConfig entity){
		sysConfigService.saveObject(entity);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doDeleteObjects")
	@ResponseBody
	public JsonResult doDeleteObjects(
			Integer... ids){
	 	sysConfigService.deleteObjects(ids);
    	return new JsonResult("delete ok");
	}

	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String name,Integer pageCurrent){
		PageObject<SysConfig> pageObject=
		sysConfigService.findPageObjects(name,
				pageCurrent);
		return new JsonResult(pageObject);
	}
	
}


