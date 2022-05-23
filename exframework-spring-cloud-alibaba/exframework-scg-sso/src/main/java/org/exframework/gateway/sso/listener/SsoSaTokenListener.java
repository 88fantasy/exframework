package org.exframework.gateway.sso.listener;

import cn.dev33.satoken.listener.SaTokenListener;
import cn.dev33.satoken.stp.SaLoginModel;

/**
 * 统一认证监听器
 *
 * @author rwe
 * @date 2021/11/12 17:50
 **/
public class SsoSaTokenListener implements SaTokenListener {


    @Override
    public void doLogin(String loginType, Object loginId, String tokenValue, SaLoginModel loginModel) {

    }

    @Override
    public void doLogout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doKickout(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doReplaced(String loginType, Object loginId, String tokenValue) {

    }

    @Override
    public void doDisable(String loginType, Object loginId, long disableTime) {

    }

    @Override
    public void doUntieDisable(String loginType, Object loginId) {

    }

    @Override
    public void doCreateSession(String id) {

    }

    @Override
    public void doLogoutSession(String id) {

    }
}
