package org.exframework.portal.auth.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * @author rwe
 * @version 创建时间：2021年4月19日 下午1:22:38
 * 登录请求
 */

@ApiModel(value = "登录请求")
public class LoginRequest {

    @NotEmpty
    @ApiModelProperty(value = "帐号", required = true)
    private String userName;

    @NotEmpty
    @ApiModelProperty(value = "密码", required = true)
    private String password;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
