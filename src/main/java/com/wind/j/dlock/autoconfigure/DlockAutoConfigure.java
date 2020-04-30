package com.wind.j.dlock.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import com.wind.j.dlock.aspect.DlockAspect;
import com.wind.j.dlock.redis.RedisExecutor;
import com.wind.j.dlock.service.ExecutorBase;
import com.wind.j.dlock.util.DlockUtil;

/**
 * 自动配置
 * 
 * @Package:com.secusoft.dlock.autoconfigure
 * @company:secusoft
 * @author:jiangpeng
 * @date:2020年4月30日 下午5:27:36
 */
@Configuration
public class DlockAutoConfigure {

	@Bean
	@ConditionalOnClass(ExecutorBase.class)
	@ConditionalOnMissingBean
	public DlockAspect dlockAspect(ExecutorBase executorBase) {
		DlockAspect dlockAspect = new DlockAspect();
		dlockAspect.setExecutorBase(executorBase);
		return dlockAspect;
	}

	@Bean
	@ConditionalOnClass(RedisTemplate.class)
	@ConditionalOnMissingBean
	public ExecutorBase redisExecutor(RedisTemplate redisTemplate) {
		RedisExecutor r = new RedisExecutor();
		r.setRedisTemplate(redisTemplate);
		DlockUtil.setExecutorBase(r);
		return r;
	}

}
