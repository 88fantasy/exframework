package org.exframework.portal.auth.sureness.handler;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.usthe.sureness.handler.SuccessHandler;
import com.usthe.sureness.subject.Subject;
import com.usthe.sureness.subject.SubjectSum;
import com.usthe.sureness.subject.creater.JwtSubjectServletCreator;
import com.usthe.sureness.util.JsonWebTokenUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 更新 token 处理
 *
 * @author rwe
 * @date 2021/9/17 14:13
 **/

public abstract class JwtRefreshTokenHandler implements SuccessHandler {

    public static final long TOKEN_EXPIRED_SECONDS = 3600;

    private long expired;

    public JwtRefreshTokenHandler() {
        this.expired = TOKEN_EXPIRED_SECONDS;
    }

    public JwtRefreshTokenHandler(long expired) {
        this.expired = expired;
    }

    @Override
    public void processHandler(SubjectSum subjectSum, Object o) {
        String account = (String)subjectSum.getPrincipal();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) o;
        HttpServletResponse response = servletRequestAttributes.getResponse();
        Subject subject = new JwtSubjectServletCreator().createSubject(o);
        Claims claims = JsonWebTokenUtil.parseJwt((String)subject.getCredential());
        Date exp = claims.getExpiration();
        long between = DateUtil.between(DateUtil.date(), exp, DateUnit.SECOND, true);
        if(between < (expired / 5)) {
            response.setHeader("Refresh-Token", getToken(account, expired));
        }
    }

    public abstract String getToken(String account, long expired);
}
