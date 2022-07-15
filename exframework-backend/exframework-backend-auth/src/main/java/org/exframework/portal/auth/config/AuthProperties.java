package org.exframework.portal.auth.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 从配置文件获取 canal 配置，前缀是 canal.client
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ConfigurationProperties(prefix = AuthConfiguration.PREFIX)
public class AuthProperties {

    private ContainerType container = ContainerType.MVC;

    /**
     * JWT properties
     * 当 authType 为 JWT 时设置的属性
     */
    private JwtProperties jwt;

    private List<DocumentAccount> accounts;

    private List<String> resourceRole;

    private List<String> excludedResource;

    public ContainerType getContainer() {
        return container;
    }

    public AuthProperties setContainer(ContainerType container) {
        this.container = container;
        return this;
    }

    public JwtProperties getJwt() {
        return jwt;
    }

    public AuthProperties setJwt(JwtProperties jwt) {
        this.jwt = jwt;
        return this;
    }

    public List<DocumentAccount> getAccounts() {
        return accounts;
    }

    public AuthProperties setAccounts(List<DocumentAccount> accounts) {
        this.accounts = accounts;
        return this;
    }

    public List<String> getResourceRole() {
        return resourceRole;
    }

    public AuthProperties setResourceRole(List<String> resourceRole) {
        this.resourceRole = resourceRole;
        return this;
    }

    public List<String> getExcludedResource() {
        return excludedResource;
    }

    public AuthProperties setExcludedResource(List<String> excludedResource) {
        this.excludedResource = excludedResource;
        return this;
    }

    public enum ContainerType {

        MVC,

        WEBFLUX
    }

    public static class DocumentAccount {

        private String user;

        private String password;

        private String name;

        private List<String> roles;

        public String getUser() {
            return user;
        }

        public DocumentAccount setUser(String user) {
            this.user = user;
            return this;
        }

        public String getPassword() {
            return password;
        }

        public DocumentAccount setPassword(String password) {
            this.password = password;
            return this;
        }

        public String getName() {
            return name;
        }

        public DocumentAccount setName(String name) {
            this.name = name;
            return this;
        }

        public List<String> getRoles() {
            return roles;
        }

        public DocumentAccount setRoles(List<String> roles) {
            this.roles = roles;
            return this;
        }
    }

    public static class AnnotationProperties {

        private boolean enable = false;

        private List<String> scanPackages;

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }

        public List<String> getScanPackages() {
            return scanPackages;
        }

        public void setScanPackages(List<String> scanPackages) {
            this.scanPackages = scanPackages;
        }
    }

    public static class JwtProperties {

        private String secret;

        public String getSecret() {
            return secret;
        }

        public void setSecret(String secret) {
            this.secret = secret;
        }
    }
}
