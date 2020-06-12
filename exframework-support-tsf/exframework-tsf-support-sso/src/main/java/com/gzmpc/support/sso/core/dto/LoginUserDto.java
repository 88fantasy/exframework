package com.gzmpc.support.sso.core.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yjf
 * @Date: 2020-5-29 11:35
 * @Description:
 */
public class LoginUserDto implements Serializable {

    private String baseRole;

    private Integer uid;

    private String username;

    private String email;

    private String telphone;




    private List<LoginUserAccountDto> loginUserAccountDtoList;


    public List<LoginUserAccountDto> getLoginUserAccountList() {
        return loginUserAccountDtoList;
    }

    public void setLoginUserAccountList(List<LoginUserAccountDto> loginUserAccountDtoList) {
        this.loginUserAccountDtoList = loginUserAccountDtoList;
    }


    public String getBaseRole() {
        return this.baseRole;
    }

    public void setBaseRole(String baseRole) {
        this.baseRole = baseRole;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    private static final long serialVersionUID = 1L;


    public Integer getUid() {
        return uid;
    }


    public void setUid(Integer uid) {
        this.uid = uid;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}

