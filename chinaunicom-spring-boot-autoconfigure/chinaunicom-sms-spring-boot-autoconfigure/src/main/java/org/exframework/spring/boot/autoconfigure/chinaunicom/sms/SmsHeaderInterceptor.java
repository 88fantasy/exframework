package org.exframework.spring.boot.autoconfigure.chinaunicom.sms;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.dtflys.forest.exceptions.ForestRuntimeException;
import com.dtflys.forest.http.ForestRequest;
import com.dtflys.forest.http.ForestRequestBody;
import com.dtflys.forest.http.ForestResponse;
import com.dtflys.forest.http.body.NameValueRequestBody;
import com.dtflys.forest.interceptor.Interceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 短信服务Header拦截器
 *
 * @author rwe
 * @date 2021/7/19 11:04
 **/
@Component
public class SmsHeaderInterceptor implements Interceptor<Object> {

    @Override
    public boolean beforeExecute(ForestRequest request) {
        Map<String, Object> data = new ConcurrentHashMap<>(16);
        List<ForestRequestBody> bodies = request.getBody();
        for (ForestRequestBody body : bodies) {
            if (NameValueRequestBody.class.isAssignableFrom(body.getClass())) {
                NameValueRequestBody nameBody = (NameValueRequestBody) body;
                data.put(nameBody.getName(), nameBody.getValue());
            }
        }
        if (data.containsKey(SmsHttpClient.SID_KEY) && data.containsKey(SmsHttpClient.TOKEN_KEY)) {
            String sid = (String) data.get(SmsHttpClient.SID_KEY);
            String token = (String) data.get(SmsHttpClient.TOKEN_KEY);
            String timeString = DateUtil.format(DateTime.now(), "yyyyMMddHHmmss");
            String initialSig = sid + token + timeString;
            String sig = DigestUtils.md5DigestAsHex(initialSig.getBytes()).toUpperCase();
            String initialAuth = sid + ":" + timeString;
            String auth = Base64.encode(initialAuth);
            request.addHeader("Authorization", auth);
            request.addQuery("sig", sig);
            return true;
        }
        return false;
    }

    @Override
    public void onSuccess(Object data, ForestRequest request, ForestResponse response) {

    }

    @Override
    public void onError(ForestRuntimeException ex, ForestRequest request, ForestResponse response) {

    }
}
