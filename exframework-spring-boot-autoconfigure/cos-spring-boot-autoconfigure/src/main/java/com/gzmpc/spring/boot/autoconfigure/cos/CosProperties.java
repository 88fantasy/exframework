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

	public class Secret {
    	
    	private String id;
    	
    	private String key;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}
    	
    }
    
    public class Cos {
    	
    	private String region;
    	
    	private Bucket bucket;

		public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public Bucket getBucket() {
			return bucket;
		}

		public void setBucket(Bucket bucket) {
			this.bucket = bucket;
		}
    	
    	
    }
    
    public class Bucket {
    	
    	private String name;
    	
    	private String path;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
    	
    	
    }
}
