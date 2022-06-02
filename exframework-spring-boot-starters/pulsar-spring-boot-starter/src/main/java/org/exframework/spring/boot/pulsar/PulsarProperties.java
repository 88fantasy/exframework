package org.exframework.spring.boot.pulsar;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 从配置文件获取 canal 配置，前缀是 canal.client
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = "pulsar.client")
public class PulsarProperties {

    /**
     * 配置信息
     */
    private Map<String, Broker> brokers = new LinkedHashMap<>();

    /**
     * 返回通道
     *
     * @param
     * @return
     */
    public Map<String, Broker> getBrokers() {
        return brokers;
    }

    /**
     * 设置通道
     *
     * @param brokers
     * @return
     */
    public void setBrokers(Map<String, Broker> brokers) {
        this.brokers = brokers;
    }

    /**
     * canal 配置类
     */
    public static class Broker {

        /**
         * 地址
         */
        String url;

        /**
         * 模式
         */
        String netModel;

        /**
         * 密钥
         */
        String secret;

        public Broker() {
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getNetModel() {
            return netModel;
        }

        public void setNetModel(String netModel) {
            this.netModel = netModel;
        }

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }

}
