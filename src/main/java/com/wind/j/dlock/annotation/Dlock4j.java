package com.wind.j.dlock.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 注解
 * 
 * @author:jiangpeng
 * @date:2020年4月30日 下午5:26:34
 */
public @interface Dlock4j {
	/**
	 * 获取锁超时时间，单位 毫秒 默认未抢到锁立即返回 可以为空
	 * 
	 * @return
	 */
	long retryTimeout() default -1;

	/**
	 * 锁的超时时间，单位 毫秒
	 * 
	 * @return
	 */
	long expireTime() default 3000;

	/**
	 * keys值，可以为空
	 * 
	 * @return
	 */
	String[] keys() default {};
}
