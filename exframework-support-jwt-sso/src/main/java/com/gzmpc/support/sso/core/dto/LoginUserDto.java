package org.exframework.support.sso.core.dto;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public boolean isSupplyer() {
        return isSupplyer;
    }

    public void setSupplyer(boolean supplyer) {
        isSupplyer = supplyer;
    }

    public boolean isCustomer() {
        return isCustomer;
    }

    public void setCustomer(boolean customer) {
        isCustomer = customer;
    }

    public boolean isEmployee() {
        return isEmployee;
    }

    public void setEmployee(boolean employee) {
        isEmployee = employee;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public List<LoginUserAccountDto> getLoginUserAccountDtoList() {
        return loginUserAccountDtoList;
    }

    public void setLoginUserAccountDtoList(List<LoginUserAccountDto> loginUserAccountDtoList) {
        this.loginUserAccountDtoList = loginUserAccountDtoList;
    }

    private boolean isSupplyer = false;

    private boolean isCustomer = false;

    private boolean isEmployee = false;

    private boolean isAdmin = false;



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

