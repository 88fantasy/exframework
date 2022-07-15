package org.exframework.portal.auth.filter;

import com.usthe.sureness.mgt.SecurityManager;
import com.usthe.sureness.processor.exception.*;
import com.usthe.sureness.subject.SubjectSum;
import com.usthe.sureness.util.SurenessContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author rwe
 * @date 2022/7/15 16:07
 **/
public class SurenessMvcFilter extends RequestContextFilter {

    private final SecurityManager securityManager;

    public SurenessMvcFilter(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    /**
     * logger
     **/
    private static final Logger logger = LoggerFactory.getLogger(SurenessMvcFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            SubjectSum subject = securityManager.checkIn(request);
            // You can consider using SurenessContextHolder to bind subject in threadLocal
            // if bind, please remove it when end
            if (subject != null) {
                SurenessContextHolder.bindSubject(subject);
            }
        } catch (IncorrectCredentialsException | UnknownAccountException | ExpiredCredentialsException e1) {
            logger.debug("this request account info is illegal, {}", e1.getMessage());
            responseWrite(ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Username or password is incorrect or token expired"), response);
            return;
        } catch (DisabledAccountException | ExcessiveAttemptsException e2) {
            logger.debug("the account is disabled, {}", e2.getMessage());
            responseWrite(ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED).body("Account is disabled"), response);
            return;
        } catch (NeedDigestInfoException e3) {
            logger.debug("you should try once again with digest auth information");
            responseWrite(ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .header("WWW-Authenticate", e3.getAuthenticate()).build(), response);
            return;
        } catch (UnauthorizedException e4) {
            logger.debug("this account can not access this resource, {}", e4.getMessage());
            responseWrite(ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("This account has no permission to access this resource"), response);
            return;
        } catch (RuntimeException e) {
            logger.error("other exception happen: ", e);
            responseWrite(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(),
                    response);
            return;
        }

        try {
            // if ok, doFilter and add subject in request
            filterChain.doFilter(request, response);
        } finally {
            SurenessContextHolder.clear();
        }
    }

    /**
     * write response json data
     *
     * @param content  content
     * @param response response
     */
    private static void responseWrite(ResponseEntity content, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        ((HttpServletResponse) response).setStatus(content.getStatusCodeValue());
        content.getHeaders().forEach((key, value) ->
                ((HttpServletResponse) response).addHeader(key, value.get(0)));
        try (PrintWriter printWriter = response.getWriter()) {
            if (content.getBody() != null) {
                printWriter.write(content.getBody().toString());
            } else {
                printWriter.flush();
            }
        } catch (IOException e) {
            logger.error("responseWrite response error: ", e);
        }
    }
}
