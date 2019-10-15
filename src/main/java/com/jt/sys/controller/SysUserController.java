package com.jt.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.JsonResult;
import com.jt.sys.entity.SysUser;
import com.jt.sys.service.SysUserService;
@RequestMapping("/user/")
@Controller
public class SysUserController {
	@Autowired
	private SysUserService sysUserService;
	
	@RequestMapping("doUserListUI")
	public String doUserListUI(){
		return "sys/user_list";
	}
	@RequestMapping("doUserEditUI")
	public String doUserEditUI(){
		return "sys/user_edit";
	}
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password){
		//1.封装用户身份和凭证
		UsernamePasswordToken token=
		new UsernamePasswordToken(username,password);
	    //2.执行登录认证操作
		Subject subject=
	    SecurityUtils.getSubject();
		subject.login(token);//认证失败此位置会抛出异常
		//如上信息提交给谁?SecurityManager
		//SecurityManager调用认证管理器完成认证操作
		return new JsonResult("login ok");
	}
	
	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		return new JsonResult(
	    sysUserService.findObjectById(id));
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
			SysUser entity,
			Integer ...roleIds){
		sysUserService.updateObject(entity,roleIds);
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysUser entity,
			Integer ...roleIds){
		sysUserService.saveObject(entity,roleIds);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(Integer id,
			Integer valid){
		sysUserService.validById(id, valid,
				"admin");
		return new JsonResult("update ok");
	}
	
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String username,Integer pageCurrent){
		return new JsonResult(
		sysUserService.findPageObjects(
		username, pageCurrent));
	}
	
}
