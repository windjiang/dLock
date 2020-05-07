package com.wind.j.dlock.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import com.wind.j.dlock.annotation.Dlock4j;
import com.wind.j.dlock.key.KeyGenerate;
import com.wind.j.dlock.service.ExecutorBase;
import com.wind.j.dlock.util.DlockUtil;

import lombok.Setter;

/**
 * 锁切面
 * 
 * @author:jiangpeng
 * @date:2020年4月30日 下午5:27:07
 */
@Aspect
public class DlockAspect {

	@Setter
	private ExecutorBase executorBase;

	@Pointcut("@annotation(com.wind.j.dlock.annotation.Dlock4j)")
	public void pointcut() {
	}

	@Around("pointcut()&&@annotation(dlock4j)")
	public void around(ProceedingJoinPoint proceedingJoinPoint, Dlock4j dlock4j) throws Throwable {
		// general key
		String key = KeyGenerate.generateKey(dlock4j, proceedingJoinPoint);
		// value
		String value = DlockUtil.getProcessId();
		boolean lock = false;
		try {
			// execute
			lock = DlockUtil.lock(key, value, dlock4j.expireTime(), dlock4j.retryTimeout());
			if (lock) {
				proceedingJoinPoint.proceed();
			}
		} finally {
			if (lock) {
				DlockUtil.unLock(key);
			}
		}

	}
}
