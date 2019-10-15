package com.jt.common.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
/**
 * 监控切面
 * @author ta
 */
@Order(3)
@Aspect
@Service
public class SysMonitorAspect {
	@Pointcut("bean(*ServiceImpl)")
	public void pointCut(){}
    /**
     * 前置通知(目标方法执行之前)
     */
	@Before("pointCut()")
	public void beforeMethod(){//例如开启事务
		System.out.println("beforeMethod");
	}
	/**
	 * 返回通知(正常结束以后，后置通知@After之后执行)
	 */
	@AfterReturning("pointCut()")
	public void returnMethod(){//例如提交事务
		System.out.println("returnMethod");
	}
	/**
	 * 异常通知(出现异常之后@After之后执行)
	 */
	@AfterThrowing("pointCut()")
	public void throwMethod(){//回滚事务
		System.out.println("throwMethod");
	}
	/**后置通知：目标方法之后执行*/
	@After("pointCut()")
	public void afterMethod(){//释放资源
		System.out.println("beforeMethod");
	}	
}

/**
 * try{
 *    @Before
 *    调用目标方法
 *    @AfterReturning
 * }catch(Exception e){
 *    @AfterThrowing
 * }finally{
 *    @After
 * }
 */










