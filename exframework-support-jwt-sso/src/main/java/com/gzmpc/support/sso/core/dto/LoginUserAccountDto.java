package com.gzmpc.support.sso.core.dto;

import java.io.Serializable;

/**
 * @Auther: yjf
 * @Date: 2020-5-29 11:35
 * @Description:
 */
public class LoginUserAccountDto implements Serializable {

    private String rootId;

    private String  rootAccount;

    private String  accountSource;

    private String  appSource;

    public String getAccountSource() {
        return accountSource;
    }

    public void setAccountSource(String accountSource) {
        this.accountSource = accountSource;
    }


    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    public String getRootAccount() {
        return rootAccount;
    }

    public void setRootAccount(String rootAccount) {
        this.rootAccount = rootAccount;
    }


    private static final long serialVersionUID = 1L;

    public String getAppSource() {
        return appSource;
    }

    public void setAppSource(String appSource) {
        this.appSource = appSource;
    }


}

