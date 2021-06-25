package org.exframework.spring.boot.autoconfigure.cos;


/**
 *
 * Author: rwe
 * Date: 2021年5月25日
 *
 * Copyright @ 2021 
 * 
 */
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