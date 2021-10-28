package org.exframework.spring.boot.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.time.Duration;

/**
 * @author rwe Date: 2021年5月20日
 * <p>
 * Copyright @ 2021
 */
@Configuration
@EnableConfigurationProperties(value = DockerProperties.class)
public class DockerAutoConfigure {

    private Logger log = LoggerFactory.getLogger(DockerAutoConfigure.class);

    @Autowired
    private DockerProperties dockerProperties;

    @Bean
    @Lazy
    @ConditionalOnMissingBean
    public DockerClient dockerHttpClient(ApacheDockerHttpClient dockerHttpClient) {

        String host = dockerProperties.getHost();
        Registry registry = dockerProperties.getRegistry();
        HttpClient5 httpClient5 = dockerProperties.getHttpClient5();

        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                .withDockerHost(host)
//                .withDockerTlsVerify(true)
//                .withDockerCertPath("/home/user/.docker")
                .withRegistryUsername(registry.getUsername())
                .withRegistryPassword(registry.getPassword())
                .withRegistryEmail(registry.getEmail())
                .withRegistryUrl(registry.getUrl())
                .build();
        DockerHttpClient httpClient = dockerHttpClient != null? dockerHttpClient: new ApacheDockerHttpClient.Builder()
                .dockerHost(config.getDockerHost())
//                .sslConfig(config.getSSLConfig())
                .maxConnections(httpClient5.getMaxConnections() != null? httpClient5.getMaxConnections() : 100)
                .connectionTimeout(Duration.ofSeconds(httpClient5.getConnectionTimeout() != null? httpClient5.getConnectionTimeout() : 30))
                .responseTimeout(Duration.ofSeconds(httpClient5.getResponseTimeout() != null? httpClient5.getResponseTimeout(): 300))
                .build();
        return DockerClientImpl.getInstance(config, httpClient);
    }
}
