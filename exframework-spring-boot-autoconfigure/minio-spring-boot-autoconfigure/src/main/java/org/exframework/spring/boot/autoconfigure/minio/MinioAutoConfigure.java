package org.exframework.spring.boot.autoconfigure.minio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;

/**
 * @author rwe Date: 2021年5月20日
 * <p>
 * Copyright @ 2021
 */
@Configuration
@EnableConfigurationProperties(value = MinioProperties.class)
public class MinioAutoConfigure {

    private Logger log = LoggerFactory.getLogger(MinioAutoConfigure.class);

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    @Lazy
    @ConditionalOnMissingBean
    public MinioTemplate minioTemplate() {
        if (minioProperties != null
                && StringUtils.hasText(minioProperties.getEndpoint())
                && StringUtils.hasText(minioProperties.getBucket())
                && StringUtils.hasText(minioProperties.getAccess())
                && StringUtils.hasText(minioProperties.getSecret())
        ) {
            try {
                log.info("自动装载配置 minio 客户端: "+ new ObjectMapper().writeValueAsString(minioProperties));
            } catch (JsonProcessingException e) {
                log.error(e.getMessage(), e);
            }
            String endPoint = minioProperties.getEndpoint(), secretKey = minioProperties.getSecret(),
                    bucketName = minioProperties.getBucket(),
                    accessKey = minioProperties.getAccess();


            try {
                return DefaultMinioTemplate.Builder.builder()
                        .endpoint(endPoint)
                        .bucket(bucketName)
                        .access(accessKey)
                        .secret(secretKey)
                        .region(minioProperties.getRegion())
                        .path(minioProperties.getPath())
                        .build();
            } catch (Exception e) {
                log.error("初始化 minio 失败:" + e.getMessage(), e);
                return new EmptyMinioTemplate();
            }
        } else {
            return new EmptyMinioTemplate();
        }
    }
}
