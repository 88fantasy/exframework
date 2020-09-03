package com.gzmpc.support.sso.core.service;


import com.gzmpc.support.sso.core.constant.SecurityConstants;
import com.gzmpc.support.sso.core.constant.UserConstants;
import com.gzmpc.support.sso.core.dto.LoginUserAccountDto;
import com.gzmpc.support.sso.core.dto.LoginUserDto;
import com.gzmpc.support.sso.core.proxy.UserCenterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginUserService {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    UserCenterService userCenterService;
    @Value("${spring.userCenter.appSource}")
    String appSource;


    public LoginUserDto getCurrentUser() {
        return (LoginUserDto) httpSession.getAttribute("currentUser");
    }


    public void doLogin() {

        String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
        String baseRole = request.getHeader(SecurityConstants.ROLE_HEADER);
        LoginUserDto user = null;
        if (StringUtils.isNotEmpty(userId)) {
            user = new LoginUserDto();
            user.setUid(Integer.valueOf(userId));
            user.setBaseRole(baseRole);

            LoginUserDto infoDto = userCenterService.getLoginUserInfo(userId);
            BeanUtils.copyProperties(infoDto, user);

            List<LoginUserAccountDto> accountList = userCenterService.getLoginUserAccountByUid(userId);
            if (accountList != null && accountList.size() > 0) {
                //指定查找相关联系统帐号
                if (StringUtils.isNotEmpty(appSource)) {
                    accountList = accountList.stream().filter(p -> p.getAppSource().equals(appSource)).collect(Collectors.toList());
                }
                user.setLoginUserAccountList(accountList);
            }

            switch (baseRole) {
                case UserConstants.USER_TYPE_ADMIN:
                    user.setAdmin(true);
                    break;
                case UserConstants.USER_TYPE_SUPPLYER:
                    user.setSupplyer(true);
                    break;
                case UserConstants.USER_TYPE_CUSTOMER:
                    user.setCustomer(true);
                    break;
                case UserConstants.USER_TYPE_EMPLOYEE:
                    user.setEmployee(true);
                    break;

                default:
        }
        }
        httpSession.setAttribute("currentUser", user);

    }
}
