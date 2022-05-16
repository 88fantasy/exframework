package org.exframework.spring.boot.captcha.annotation;

import java.lang.annotation.*;

/**
 * 需要校验验证码
 *
 * @author rwe
 * @date 2022/5/15 16:18
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CaptchaRequired {

    /**
     * 链接
     * @return
     */
    String[] value() default {};

}
