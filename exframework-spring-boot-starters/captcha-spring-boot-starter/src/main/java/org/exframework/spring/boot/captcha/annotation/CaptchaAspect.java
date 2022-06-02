//package org.exframework.spring.boot.captcha.annotation;
//
//import com.anji.captcha.model.common.ResponseModel;
//import com.anji.captcha.model.vo.CaptchaVO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.StringUtils;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author rwe
// * @date 2021/10/15 00:19
// **/
//@Aspect
//public class CaptchaAspect {
//
//    private static final Logger log = LoggerFactory.getLogger(CaptchaAspect.class);
//
//    private static final String CAPTCHA_HEADER = "captcha";
//
//
//    // 可切类或者切方法
//    @Around("@annotation(org.exframework.spring.boot.captcha.annotation.CaptchaRequired)||@within(org.exframework.spring.boot.captcha.annotation.CaptchaRequired)")
//    public Object captchaRequired(ProceedingJoinPoint joinPoint, CaptchaRequired captchaRequired) throws Throwable {
//        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = servletRequestAttributes.getRequest();
//        String[] links = captchaRequired.value();
//        String path = request.getPath().value();
//        if (captchaLinks.stream().anyMatch(link -> path.equals(link))) {
//            String captchaJson = request.getHeaders().getFirst(header);
//            if (!StringUtils.hasText(captchaJson)) {
//                return getVoidMono(exchange, "header 缺少验证码");
//            }
//            try {
//                CaptchaVO captchaVO = new ObjectMapper().readValue(captchaJson, CaptchaVO.class);
//                ResponseModel rm = captchaService.verification(captchaVO);
//                if (!rm.isSuccess()) {
//                    return getVoidMono(exchange, "验证码错误:" + rm.getRepMsg());
//                }
//            } catch (JsonProcessingException e) {
//                String msg = "验证码格式错误:" + e.getMessage();
//                logger.error(msg, e);
//                return getVoidMono(exchange, msg);
//            }
//        }
//        return joinPoint.proceed();
//    }
//
//
//}
