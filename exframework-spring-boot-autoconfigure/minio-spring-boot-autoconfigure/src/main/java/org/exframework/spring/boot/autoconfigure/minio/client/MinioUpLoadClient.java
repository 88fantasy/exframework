package org.exframework.spring.boot.autoconfigure.minio.client;

import io.minio.*;
import io.minio.errors.MinioException;
import org.exframework.spring.boot.autoconfigure.minio.errors.MinioClientException;
import org.exframework.spring.boot.autoconfigure.minio.model.PutObjectRequest;
import org.exframework.spring.boot.autoconfigure.minio.model.UploadResult;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLConnection;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public interface MinioUpLoadClient extends CommonClient {

    Map<String, String> DEFAULT = new ConcurrentHashMap<>();

    /**
     * 上传文件
     *
     * @param file      本地文件
     * @param targetUrl 远端路径(包含文件夹)
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(File file, String targetUrl) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, file).setUserMetadata(DEFAULT);
        return upload(request, -1, true);
    }

    /**
     * 上传文件流
     *
     * @param fileInputStream 文件流
     * @param targetUrl       远端路径(包含文件夹)
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(FileInputStream fileInputStream, String targetUrl) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, fileInputStream, DEFAULT);
        return upload(request, -1, true);
    }


    /**
     * 上传文件流,并指定文件信息
     *
     * @param fileInputStream 文件流
     * @param targetUrl       远端路径(包含文件夹)
     * @param userMetadata    文件信息
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(FileInputStream fileInputStream, String targetUrl, Map<String, String> userMetadata) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, fileInputStream, userMetadata);
        return upload(request, -1, true);
    }


    /**
     * 上传文件,并指定文件信息
     *
     * @param file         本地文件
     * @param targetUrl    远端路径(包含文件夹)
     * @param userMetadata 文件信息
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(File file, String targetUrl, Map<String, String> userMetadata) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, file).setUserMetadata(userMetadata);
        return upload(request, -1, true);
    }

    /**
     * 上传文件流,指定文件信息,并生成预览地址
     *
     * @param fileInputStream 文件流
     * @param targetUrl       远端路径(包含文件夹)
     * @param userMetadata    文件信息
     * @param expirationTime  预览有效时间(ms)
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(FileInputStream fileInputStream, String targetUrl, Map<String, String> userMetadata, int expirationTime) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, fileInputStream, userMetadata);
        return upload(request, expirationTime, true);
    }

    /**
     * 上传文件,指定文件信息,并生成预览地址
     *
     * @param file           本地文件
     * @param targetUrl      远端路径(包含文件夹)
     * @param userMetadata   文件信息
     * @param expirationTime 预览有效时间(ms)
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(File file, String targetUrl, Map<String, String> userMetadata, int expirationTime) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, file).setUserMetadata(userMetadata);
        return upload(request, expirationTime, true);
    }

    /**
     * 上传文件流,指定文件信息,并生成预览地址, 可覆盖
     *
     * @param fileInputStream 本地文件流
     * @param targetUrl       远端路径(包含文件夹)
     * @param userMetadata    文件信息
     * @param expirationTime  预览有效时间(ms)
     * @param replace         true if replace
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(FileInputStream fileInputStream, String targetUrl, Map<String, String> userMetadata, int expirationTime, boolean replace) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, fileInputStream, userMetadata);
        return upload(request, expirationTime, replace);
    }


    /**
     * 上传文件,指定文件信息,并生成预览地址, 可覆盖
     *
     * @param file           本地文件
     * @param targetUrl      远端路径(包含文件夹)
     * @param userMetadata   文件信息
     * @param expirationTime 预览有效时间(ms)
     * @param replace        true if replace
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(File file, String targetUrl, Map<String, String> userMetadata, int expirationTime, boolean replace) throws MinioClientException, MinioException {
        PutObjectRequest request = new PutObjectRequest(getBucketName(), targetUrl, file).setUserMetadata(userMetadata);
        return upload(request, expirationTime, replace);
    }

    /**
     * 上传文件
     *
     * @param request        文件请求
     * @param expirationTime 预览有效时间(ms)
     * @param replace        true if replace
     * @return 上传结果
     * @throws MinioClientException
     * @throws MinioException
     */
    default UploadResult upload(PutObjectRequest request, int expirationTime, boolean replace) throws MinioClientException, MinioException {

        String bucketName = request.getBucketName();
        String requestKey = getKey(request.getKey());

        PutObjectArgs.Builder builder = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(requestKey);
        if (StringUtils.hasText(request.getContentType())) {
            builder.contentType(request.getContentType());
        } else {
            builder.contentType(URLConnection.getFileNameMap().getContentTypeFor(requestKey));
        }
        if (!ObjectUtils.isEmpty(request.getUserMetadata())) {
            builder.userMetadata(request.getUserMetadata());
        }
        if (request.getInputStream() != null) {
            builder.stream(request.getInputStream(), -1, 10485760);
        } else if (request.getFile() != null) {
            File file = request.getFile();
            if (file.exists()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    builder.stream(fileInputStream, -1, 10485760);
                } catch (IOException e) {
                    throw new MinioClientException("访问文件失败:" + file.getName());
                }
            } else {
                throw new MinioClientException(file.getName() + "不存在");
            }
        } else {
            throw new MinioClientException("请指定文件");
        }

        MinioClient minioClient = getMinioClient();
        if (!StringUtils.hasLength(bucketName)) {
            throw new MinioClientException("bucketName不能为null");
        }

        try {

            if (!replace) {
                try {
                    StatObjectResponse statObjectResponse = minioClient.statObject(StatObjectArgs.builder()
                            .bucket(bucketName)
                            .object(requestKey).build());
                    return new UploadResult(statObjectResponse.headers(), statObjectResponse.bucket(), statObjectResponse.region(), statObjectResponse.object(), statObjectResponse.etag(), statObjectResponse.versionId());
                } catch (Exception e) {
                    // 如果不存在会抛出异常
                }
            }
            ObjectWriteResponse response = minioClient.putObject(builder.build());
            if (expirationTime > 0) {
                minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                        .bucket(response.bucket())
                        .object(response.object())
                        .expiry(expirationTime)
                        .build());
            }
            return new UploadResult(response.headers(), response.bucket(), response.region(), response.object(), response.etag(), response.versionId());
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new MinioClientException(e);
        }
    }
}
