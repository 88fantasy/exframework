package org.exframework.spring.boot.minio.model;

import io.minio.StatObjectResponse;
import okhttp3.Headers;

/**
 * 下载结果
 *
 * @author rwe
 * @date 2021/8/27 10:32
 **/
public class DownloadResult extends StatObjectResponse {

    public DownloadResult(Headers headers, String bucket, String region, String object) {
        super(headers, bucket, region, object);
    }
}
