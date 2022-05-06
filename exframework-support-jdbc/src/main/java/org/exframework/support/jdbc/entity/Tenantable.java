package org.exframework.support.jdbc.entity;

/**
 * 多租户实体
 *
 * @author rwe
 * @date 2022/5/5 15:14
 **/
public interface Tenantable {

    String getTenantId();
}
