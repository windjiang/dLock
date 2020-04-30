package com.wind.j.dlock.key;

import org.aspectj.lang.ProceedingJoinPoint;

import com.wind.j.dlock.annotation.Dlock4j;
import com.wind.j.dlock.util.SpelUtil;

/**
 * key generator
 * 
 * @author:jiangpeng
 * @date:2020年4月23日 上午9:37:53
 */
public class KeyGenerate {

	public static String generateKey(Dlock4j dlock4j, ProceedingJoinPoint joinPoint) {

		if (joinPoint == null) {
			return "";
		}

		StringBuilder keyRet = new StringBuilder();

		for (String key : dlock4j.keys()) {
			if (key == null || key.equals("")) {
				continue;
			}

			if (key.indexOf("#") < 0) {
				if (keyRet.toString().equals("")) {
					keyRet.append(key);
				} else {
					keyRet.append("-").append(key);
				}

			} else {
				if (keyRet.toString().equals("")) {
					keyRet.append(SpelUtil.generateKeyBySpEL(key, joinPoint));
				} else {
					keyRet.append("-").append(SpelUtil.generateKeyBySpEL(key, joinPoint));
				}
			}
		}
		// 注解key
		if (!keyRet.toString().equals("")) {
			return keyRet.toString();
		}

		// 注解中keys为空，返回报名+方法名作为key
		Class<?> cl = joinPoint.getTarget().getClass();
		keyRet.append(cl.getPackage()).append(":").append(cl.getName()).append(":")
				.append(joinPoint.getSignature().getName());

		if (dlock4j == null || dlock4j.keys() == null) {
			return keyRet.toString();
		}

		return keyRet.toString();
	}
}
