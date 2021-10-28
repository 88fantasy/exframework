package org.exframework.spring.boot.minio.model;

import io.minio.ObjectWriteResponse;
import okhttp3.Headers;

/**
 * 上传结果
 *
 * @author rwe
 * @date 2021/8/27 10:32
 **/
public class UploadResult extends ObjectWriteResponse {

    public UploadResult(Headers headers, String bucket, String region, String object, String etag, String versionId) {
        super(headers, bucket, region, object, etag, versionId);
    }

}
