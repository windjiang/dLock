package com.wind.j.dlock.service;

/**
 * execute base
 * 
 * @author:jiangpeng
 * @date:2020年4月30日 下午5:31:38
 */
public interface ExecutorBase {
	/**
	 * lock
	 * 
	 * @param key
	 * @param value
	 * @param timeout
	 * @return
	 */
	boolean lock(String key, String value, long timeout);

	/**
	 * unlock
	 * 
	 * @param key
	 */
	void unlock(String key);
}
