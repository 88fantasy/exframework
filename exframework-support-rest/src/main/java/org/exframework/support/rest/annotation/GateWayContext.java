package org.exframework.support.rest.annotation;

import org.exframework.support.common.annotation.ThreadData;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.rest.exception.ApiException;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({METHOD, ANNOTATION_TYPE})
/**
 * @author rwe
 * @version 创建时间：2021年5月21日 下午4:42:56
 * 访问变量
 */
@ThreadData
public @interface GateWayContext {

    String USER_ID = "userId";
    String USER_NAME = "userName";
    String IDENTIFICATION_ID = "identificationId";
    String IDENTIFICATION_NAME = "identificationName";
    String TENANT_ID = "tenantId";
    String APP_ID = "appId";

    String CONTEXT_KEY = "__gateWayContext";


    class Context {

        private Long userId;
        private String userName;
        private Long identificationId;
        private String identificationName;
        private Long tenantId;
        private Long appId;

        public Context(Long userId, String userName, Long identificationId, String identificationName, Long tenantId, Long appId) {
            this.userId = userId;
            this.userName = userName;
            this.identificationId = identificationId;
            this.identificationName = identificationName;
            this.tenantId = tenantId;
            this.appId = appId;
        }

        public static void bind(HttpServletRequest request) {
            Long userId = null;
            String userName = request.getHeader(USER_NAME);
            Long identificationId = null;
            String identificationName = request.getHeader(IDENTIFICATION_NAME);
            Long tenantId = null;
            Long appId = null;

            String userIdString = request.getHeader(USER_ID);
            String identificationIdString = request.getHeader(IDENTIFICATION_ID);
            String tenantIdString = request.getHeader(TENANT_ID);
            String appIdString = request.getHeader(APP_ID);

            try {
                if (StrUtils.hasText(userIdString)) {
                    userId = Long.parseLong(userIdString);
                }
                if (StrUtils.hasText(identificationIdString)) {
                    identificationId = Long.parseLong(identificationIdString);
                }
                if (StrUtils.hasText(tenantIdString)) {
                    tenantId = Long.parseLong(tenantIdString);
                }
                if (StrUtils.hasText(appIdString)) {
                    appId = Long.parseLong(appIdString);
                }
            } catch (NumberFormatException e) {
                throw new ApiException("header格式错误");
            }

            ThreadData.ThreadDataClass.bind(CONTEXT_KEY, new Context(
                            userId,
                    userName,
                            identificationId,
                    identificationName,
                            tenantId,
                            appId
                    )
            );
        }

        public static void set(Context context) {
            ThreadData.ThreadDataClass.bind(CONTEXT_KEY, context);
        }

        public static Context get() {
            return (Context) ThreadData.ThreadDataClass.getBind(CONTEXT_KEY);
        }


        public Long getUserId() {
            return userId;
        }

        public String getUserName() {
            return userName;
        }

        public Long getIdentificationId() {
            return identificationId;
        }

        public String getIdentificationName() {
            return identificationName;
        }

        public Long getTenantId() {
            return tenantId;
        }

        public Long getAppId() {
            return appId;
        }
    }
}
