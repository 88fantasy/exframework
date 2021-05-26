package com.gzmpc.spring.boot.autoconfigure.cos;

import org.springframework.boot.context.properties.ConfigurationProperties;
import static com.gzmpc.spring.boot.autoconfigure.cos.CosProperties.DEFAULT_PREFIX;

/**
 *
 * Author: rwe
 * Date: 2021年5月20日
 *
 * Copyright @ 2021 
 * 
 */
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class CosProperties {

	/**
     * PREFIX
     */
    public static final String DEFAULT_PREFIX = "tencentcloud";
    
    private String deverloper;
    
    private Secret secret;
    
    private Cos cos;
    
    
    public String getDeverloper() {
		return deverloper;
	}

	public void setDeverloper(String deverloper) {
		this.deverloper = deverloper;
	}

	public Secret getSecret() {
		return secret;
	}

	public void setSecret(Secret secret) {
		this.secret = secret;
	}

	public Cos getCos() {
		return cos;
	}

	public void setCos(Cos cos) {
		this.cos = cos;
	}
    
    
    
    
}
