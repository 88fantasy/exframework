package com.gzmpc.support.sso.core.service;


import com.alibaba.fastjson.JSONObject;

import com.gzmpc.support.sso.core.dto.LoginUserDto;
import com.gzmpc.support.sso.core.utils.HttpClientUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Service
public class LoginUserService {
    @Autowired
    private  HttpServletRequest request;
    @Autowired
    private  HttpSession httpSession ;

    static private Log log = LogFactory.getLog(LoginUserService.class.getName());


    public LoginUserDto getCurrentUser(HttpServletRequest request) {
        if(request!=null){
            httpSession = request.getSession();
            return (LoginUserDto) httpSession.getAttribute("ssoLoginUser");
        }else {
            return null;
        }
    }

    public LoginUserDto getCurrentUser() {
        if(httpSession!=null){
            return (LoginUserDto) httpSession.getAttribute("ssoLoginUser");
        }else {
          return null;
        }
    }

    public  LoginUserService (){
        if( RequestContextHolder.getRequestAttributes()!=null) {
            this.request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            if (request != null) {
                this.httpSession = request.getSession();
            }
        }
    }

    public  LoginUserService (HttpServletRequest request){
        this.request = request;
        if (request != null) {
            this.httpSession = request.getSession();
        }

    }

    public void setLoginUserFromServer(String serverUrl, String authorization) throws Exception {
        if(request!=null&&request.getAttribute("ssoLoginUser")==null)
        {
        JSONObject param = new JSONObject();
        JSONObject obj = null;
        try {
            obj = HttpClientUtils.postJsonRetObject(serverUrl, authorization, param);
        } catch (Exception e) {
            log.error("获取登录用户信息失败-", e);
            throw  e;
        }
        LoginUserDto loginUserDto = (LoginUserDto) JSONObject.toJavaObject(obj, LoginUserDto.class);
        httpSession.setAttribute("ssoLoginUser",loginUserDto);
    }
    }
}
