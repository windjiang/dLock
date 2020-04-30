package com.wind.j.dlock.service;

/**
 * execute base
 * 
 * @author:jiangpeng
 * @date:2020年4月30日 下午5:31:38
 */
public interface ExecutorBase {
	boolean lock(String key, String value, long timeout);

	void unlock(String key);
}
