package org.exframework.spring.boot.minio.client;

import io.minio.DownloadObjectArgs;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.errors.MinioException;
import io.minio.http.Method;
import org.exframework.spring.boot.minio.errors.MinioClientException;
import org.exframework.spring.boot.minio.model.DownloadResult;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

public interface MinioDownLoadClient extends CommonClient {


    /**
     * 下载文件
     *
     * @param key  源路径
     * @param file 目标文件
     * @return
     * @throws MinioException
     * @throws MinioClientException
     */
    default DownloadResult download(String key, File file) throws MinioException, MinioClientException {
        return downFileToLocal(key, file.getName());
    }

    /**
     * 下载文件
     *
     * @param key    源路径
     * @param target 目标文件路径
     * @return
     * @throws MinioException
     * @throws MinioClientException
     */
    default DownloadResult downFileToLocal(String key, String target) throws MinioException, MinioClientException {

        try {
            DownloadResult downloadResult = statObject(getKey(key));
            File targetFile = new File(target);
            //判断目标文件所在的目录是否存在
            if (!targetFile.getParentFile().exists()) {
                //如果目标文件所在的目录不存在，则创建父目录
                targetFile.getParentFile().mkdirs();
            }
            getMinioClient().downloadObject(DownloadObjectArgs.builder()
                    .bucket(getBucketName())
                    .object(downloadResult.object())
                    .filename(target)
                    .build());
            return downloadResult;
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new MinioClientException(e);
        }
    }

    /**
     * 生成下载文件地址
     *
     * @param key      源路径
     * @param duration 时间整数
     * @param unit     时间单位
     * @return
     * @throws MinioException
     * @throws MinioClientException
     */
    default String downFileWithUrl(String key, int duration, TimeUnit unit) throws MinioException, MinioClientException {
        try {
            return getMinioClient().getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .method(Method.GET)
                    .bucket(getBucketName())
                    .object(getKey(key))
                    .expiry(duration, unit)
                    .build());
        } catch (InvalidKeyException | IOException | NoSuchAlgorithmException e) {
            throw new MinioClientException(e);
        }
    }
}
