package com.jt.common.util;

import org.apache.shiro.SecurityUtils;

import com.jt.sys.entity.SysUser;

public class ShiroUtils {

	 public static SysUser getPrincipal(){
		 return (SysUser)SecurityUtils
		.getSubject().getPrincipal();
	 }
}
