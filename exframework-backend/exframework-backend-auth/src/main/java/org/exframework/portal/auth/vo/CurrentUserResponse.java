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

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "岗位")
    private String title;

    @ApiModelProperty(value = "组织架构")
    private String group;

    @ApiModelProperty(value = "权限集")
    private List<String> permissions;


    public CurrentUserResponse(@NotNull String userid, String name, String email) {
        this(userid, name, email, null, null);
    }


    public CurrentUserResponse(@NotNull String userid, String name, String email, String title, String group) {
        this(userid, name, email, title, group, null);
    }

    public CurrentUserResponse(@NotNull String userid, String name, String email, String title, String group,
                               List<String> permissions) {
        this.userid = userid;
        this.name = name;
        this.email = email;
        this.title = title;
        this.group = group;
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

    public String getEmail() {
        return email;
    }

    public CurrentUserResponse setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CurrentUserResponse setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getGroup() {
        return group;
    }

    public CurrentUserResponse setGroup(String group) {
        this.group = group;
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
