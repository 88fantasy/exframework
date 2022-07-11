package org.exframework.portal.auth.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author rwe
 * @version 创建时间：2021年4月19日 下午1:35:05
 * 当前登录返回
 */

@ApiModel(value = "当前登录返回")
public class CurrentUserResponse {

    public static final String CURRENT_USER_KEY = "__currentUser";

    @NotNull
    @ApiModelProperty(value = "帐号", required = true)
    private String userid;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "权限集")
    private List<String> permissions;


    public CurrentUserResponse(@NotNull String userid, String name) {
        this(userid, name, null);
    }


    public CurrentUserResponse(@NotNull String userid, String name,
                               List<String> permissions) {
        this.userid = userid;
        this.name = name;
        this.permissions = permissions;
    }

    public String getUserid() {
        return userid;
    }

    public CurrentUserResponse setUserid(String userid) {
        this.userid = userid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CurrentUserResponse setName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public CurrentUserResponse setPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }
}
