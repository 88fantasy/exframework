package org.exframework.portal.auth.filter;

import com.usthe.sureness.mgt.SecurityManager;
import com.usthe.sureness.mgt.SurenessSecurityManager;
import com.usthe.sureness.processor.exception.ExpiredCredentialsException;
import com.usthe.sureness.processor.exception.IncorrectCredentialsException;
import com.usthe.sureness.processor.exception.UnauthorizedException;
import com.usthe.sureness.processor.exception.UnknownAccountException;
import com.usthe.sureness.subject.SubjectSum;
import com.usthe.sureness.util.SurenessContextHolder;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author rwe
 * @date 2022/7/15 16:57
 **/
public class SurenessWebfluxFilter implements WebFilter {

    private final SecurityManager securityManager;

    public SurenessWebfluxFilter(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    @NotNull
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, @NotNull WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        Integer statusCode = null;
        String errorMsg = null;
        try {
            SubjectSum subject = securityManager.checkIn(request);
            // You can consider using SurenessContextHolder to bind subject in threadLocal
            // if bind, please remove it when end
            if (subject != null) {
                SurenessContextHolder.bindSubject(subject);
            }
        } catch (IncorrectCredentialsException | UnknownAccountException | ExpiredCredentialsException e1) {
            statusCode = HttpStatus.UNAUTHORIZED.value();
            errorMsg = e1.getMessage();
        } catch (UnauthorizedException e5) {
            statusCode = HttpStatus.FORBIDDEN.value();
            errorMsg = e5.getMessage();
        } catch (RuntimeException e) {
            statusCode = HttpStatus.CONFLICT.value();
            errorMsg = e.getMessage();
        }

        // auth error filter to error collect api
        if (statusCode != null && errorMsg != null) {
            String finalErrorMsg = errorMsg;
            Integer finalStatusCode = statusCode;
            request = request.mutate().headers(httpHeaders -> {
                httpHeaders.add("statusCode", String.valueOf(finalStatusCode));
                httpHeaders.add("errorMsg", finalErrorMsg);
            }).path("/auth/error").build();
            exchange = exchange.mutate().request(request).build();
        }
        return chain.filter(exchange).doFinally(x -> SurenessContextHolder.unbindSubject());
    }
}