package org.exframework.portal.auth.service;


import com.usthe.sureness.mgt.SurenessSecurityManager;
import com.usthe.sureness.processor.exception.*;
import com.usthe.sureness.subject.SubjectSum;
import org.exframework.portal.auth.dto.LoginRequest;
import org.exframework.portal.auth.entity.SysUser;
import org.exframework.portal.auth.vo.CurrentUserResponse;
import org.exframework.portal.auth.vo.LoginResponse;
import org.exframework.support.rest.enums.ResultCode;
import org.exframework.support.rest.exception.ServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author rwe
 * @version 创建时间：2021年4月19日 下午11:08:41 登录业务实现类
 */

@Service
public class LoginService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    HttpServletRequest request;

    @Autowired
    UserService userService;

    public CurrentUserResponse currentUser() {
        try {
            SubjectSum subject = SurenessSecurityManager.getInstance().checkIn(request);
            if (subject != null) {
                SysUser user = userService.loadAccount((String) subject.getPrincipal());
                return new CurrentUserResponse(user.getUserId(), user.getName())
                        .setPermissions(userService.permissions(user.getUserId()));
            }
        } catch (ProcessorNotFoundException | UnknownAccountException | UnsupportedSubjectException e4) {
            logger.error("this request is illegal", e4);
            throw new ServerException(e4.getMessage());
        } catch (DisabledAccountException | ExcessiveAttemptsException e2) {
            logger.error("the account is disabled", e2);
            throw new ServerException(ResultCode.UNAUTHORIZED.getCode(), "帐号已被禁用");
        } catch (IncorrectCredentialsException e3) {
            logger.error("this account credential is incorrect", e3);
            throw new ServerException(ResultCode.UNAUTHORIZED.getCode(), "认证已失效");
        } catch (ExpiredCredentialsException e4) {
            logger.error("this account credential is expired", e4);
            throw new ServerException(ResultCode.UNAUTHORIZED.getCode(), "认证已过期");
        } catch (UnauthorizedException e5) {
            logger.error("this account can not access this resource", e5);
            throw new ServerException(ResultCode.FORBIDDEN.getCode(), e5.getMessage());
        } catch (RuntimeException e) {
            logger.error("other exception happen: ", e);
            throw new ServerException(e.getMessage());
        }

        throw new ServerException();
    }


    public LoginResponse login(LoginRequest login) {
        return userService.login(login.getUserName(), login.getPassword());
    }

}
