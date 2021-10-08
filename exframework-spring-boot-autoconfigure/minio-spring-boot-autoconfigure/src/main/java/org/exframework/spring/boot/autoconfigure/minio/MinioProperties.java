package org.exframework.spring.boot.autoconfigure.minio;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;

/**
 * minio配置类
 * @author rwe
 * @since 2021年5月20日
 *
 * Copyright @ 2021 
 * 
 */
@ConfigurationProperties(value = MinioProperties.DEFAULT_PREFIX)
public class MinioProperties {

	/**
     * PREFIX
     */
    public static final String DEFAULT_PREFIX = "minio";

	/**
	 * 服务器地址
	 */
	private String endpoint;

	/**
	 * 存储桶
	 */
	@Nullable
	private String bucket;

	/**
	 * 相对路径
	 */
	@Nullable
    private String path;

	/**
	 * 可用区
	 */
	@Nullable
	private String region;

	/**
	 * 帐号
	 */
	private String access;

	/**
	 * 密码
	 */
    private String secret;


	public String getEndpoint() {
		return endpoint;
	}

	public MinioProperties setEndpoint(String endpoint) {
		this.endpoint = endpoint;
		return this;
	}

	public String getBucket() {
		return bucket;
	}

	public MinioProperties setBucket(String bucket) {
		this.bucket = bucket;
		return this;
	}

	public String getPath() {
		return path;
	}

	public MinioProperties setPath(String path) {
		this.path = path;
		return this;
	}

	@Nullable
	public String getRegion() {
		return region;
	}

	public MinioProperties setRegion(@Nullable String region) {
		this.region = region;
		return this;
	}

	public String getAccess() {
		return access;
	}

	public MinioProperties setAccess(String access) {
		this.access = access;
		return this;
	}

	public String getSecret() {
		return secret;
	}

	public MinioProperties setSecret(String secret) {
		this.secret = secret;
		return this;
	}
}
