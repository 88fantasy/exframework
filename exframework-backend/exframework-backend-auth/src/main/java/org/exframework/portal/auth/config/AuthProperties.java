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

    private String user;

    private String password;

    private String name;

    private List<String> permissions;

    private List<String> resourceRole;

    private List<String> excludedResource;

    public String getUser() {
        return user;
    }

    public AuthProperties setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AuthProperties setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthProperties setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public AuthProperties setPermissions(List<String> permissions) {
        this.permissions = permissions;
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

}
