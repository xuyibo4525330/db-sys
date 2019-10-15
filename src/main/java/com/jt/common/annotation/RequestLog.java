package com.jt.common.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author 速度
 * @Target 用于描述自定义的注解能够描述哪些对象
 * @Retention 用于描述自定义的注解何时有效
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestLog {//RequestLog.class
	 String value() default "";
}

