package com.wind.j.dlock.util;

import org.springframework.util.Assert;

import com.wind.j.dlock.service.ExecutorBase;

import lombok.extern.slf4j.Slf4j;

/**
 * dLock util
 * 
 * @author:jiangpeng
 * @date:2020年4月30日 下午5:31:58
 */
@Slf4j
public class DlockUtil {

	private static final String PROCESS_ID = LocalUtil.getLocalMAC() + LocalUtil.getJvmPid();
	/**
	 * 重新尝试的时间间隔
	 */
	private static long retryIntervalTime = 100;

	private static ExecutorBase executorBase;

	public static boolean lock(String key, String value, long expireTime, Long retryTime) throws InterruptedException {

		Assert.isTrue(expireTime > 0, "error timeout is less 0");
		long continueTime = 0;

		while (!executorBase.lock(key, value, expireTime)) {
			if (retryTime != null && retryTime > 0 && continueTime <= retryTime) {
				continueTime += retryIntervalTime;
				Thread.sleep(retryIntervalTime);
				continue;
			}
			// throw error
			log.error("lock failed , {} is locked", key);
			return false;
		}

		return true;
	}

	public static boolean lock(String key, long expireTime, Long retryTime) throws InterruptedException {
		return lock(key, PROCESS_ID, expireTime, retryTime);
	}

	public static void unLock(String key) {
		executorBase.unlock(key);
	}

	public static String getProcessId() {
		return PROCESS_ID;
	}

	public static void setExecutorBase(ExecutorBase executorBase) {
		DlockUtil.executorBase = executorBase;
	}

}
