package org.exframework.gateway.sso.filter;

import com.anji.captcha.service.CaptchaService;
import org.exframework.spring.boot.captcha.filter.AbstractCaptchaReactorFilter;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;

import java.util.Collection;

/**
 * 验证码过滤器
 *
 * @author rwe
 * @date 2022/6/3 20:58
 **/
public class DefaultCaptchaReactorFilter extends AbstractCaptchaReactorFilter {

    public DefaultCaptchaReactorFilter(CaptchaService captchaService, Collection<String> captchaLinks) {
        super(captchaService, captchaLinks);
    }

    public DefaultCaptchaReactorFilter(String header, CaptchaService captchaService, Collection<String> captchaLinks) {
        super(header, captchaService, captchaLinks);
    }

    @Override
    public Object responseFrom(String message) {
        return new ApiResponseData<>(ResultCode.BAD_REQUEST, message, null);
    }
}
