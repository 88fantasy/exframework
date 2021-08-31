package org.exframework.spring.boot.autoconfigure.minio.model;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * 上传文件请求
 * @author rwe
 * @date 2021/8/26 20:07
 **/
public class PutObjectRequest {

    /**
     * 存储桶
     */
    private String bucketName;

    /**
     * 目标文件路径(含文件名)
     */
    private String key;

    /**
     * 上传文件
     */
    private File file;

    /**
     * 上传流
     */
    private transient InputStream inputStream;

    /**
     * 指定contentType
     */
    private String contentType;

    private Map<String,String> userMetadata;

    public PutObjectRequest() {
    }

    public PutObjectRequest(String bucketName, String key, File file) {
        this.bucketName = bucketName;
        this.key = key;
        this.file = file;
    }

    public PutObjectRequest(String bucketName, String key, InputStream inputStream, Map<String,String> userMetadata) {
        this.bucketName = bucketName;
        this.key = key;
        this.inputStream = inputStream;
        this.userMetadata = userMetadata;
    }

    public String getBucketName() {
        return bucketName;
    }

    public PutObjectRequest setBucketName(String bucketName) {
        this.bucketName = bucketName;
        return this;
    }

    public String getKey() {
        return key;
    }

    public PutObjectRequest setKey(String key) {
        this.key = key;
        return this;
    }

    public File getFile() {
        return file;
    }

    public PutObjectRequest setFile(File file) {
        this.file = file;
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public PutObjectRequest setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public PutObjectRequest setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public Map<String, String> getUserMetadata() {
        return userMetadata;
    }

    public PutObjectRequest setUserMetadata(Map<String, String> userMetadata) {
        this.userMetadata = userMetadata;
        return this;
    }
}
