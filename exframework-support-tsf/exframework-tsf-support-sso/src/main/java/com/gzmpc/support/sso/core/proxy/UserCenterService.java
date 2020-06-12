package com.gzmpc.support.sso.core.proxy;

import com.gzmpc.support.sso.core.config.FeignHeaderConfig;
import com.gzmpc.support.sso.core.dto.LoginUserAccountDto;
import com.gzmpc.support.sso.core.dto.LoginUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: yjf
 * @Date: 2020-5-21 20:23
 * @Description:
 */

@FeignClient(name = "user-center-service",configuration = FeignHeaderConfig.class)
public interface UserCenterService {


    /**
     * @param uid
     * 获取用户
     * @return
     */
    @RequestMapping(value = "userProvider/getLoginUserAccountByUid", method = RequestMethod.GET)
    public List<LoginUserAccountDto> getLoginUserAccountByUid(@RequestParam("uid") String uid);

    @RequestMapping(value = "userProvider/getLoginUserInfoByUid", method = RequestMethod.GET)
    public LoginUserDto getLoginUserInfo(@RequestParam("uid") String uid);

}
