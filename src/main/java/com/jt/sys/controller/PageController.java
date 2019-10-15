package com.jt.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class PageController {
	/**
	 * 返回系统的首页页面
	 * @return
	 */
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "starter";//starter.html
	}//视图解析器的后缀使用.html
	
	/**返回分页页面(列表页面中的一部分):
	 * 实就是一个div元素*/
	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
	
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
	   return "login";
	}
}




