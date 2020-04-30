package com.wind.j.dlock.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "dLock.source")
@Data
/**
 * multi source config, default redis
 * 
 * @author jiangpeng
 *
 */
public class DLockSourceProperties {

	private String type;

}
