package org.exframework.portal.auth.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.usthe.sureness.util.SurenessContextHolder;
import org.apache.ibatis.reflection.MetaObject;
import org.exframework.portal.auth.annotation.AutoCurrentAccount;
import org.exframework.portal.auth.annotation.AutoCurrentAccountName;
import org.exframework.portal.auth.vo.CurrentUserResponse;
import org.springframework.util.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.util.function.Function;

/**
 * @author rwe
 * @version 创建时间：2021年5月21日 下午4:49:01
 * 自动设置当前登录帐号
 */

public class CurrentAccountMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        fill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        fill(metaObject);
    }

    private void fill(MetaObject metaObject) {
        setAccount(metaObject, AutoCurrentAccount.class, CurrentUserResponse::getUserid);
        setAccount(metaObject, AutoCurrentAccountName.class, CurrentUserResponse::getName);
    }

    private void setAccount(MetaObject metaObject, Class<? extends Annotation> clazz, Function<CurrentUserResponse, Object> func) {
        Object o = metaObject.getOriginalObject();
        ReflectionUtils.doWithFields(o.getClass(), field -> {
                    CurrentUserResponse currentUserResponse = (CurrentUserResponse) SurenessContextHolder.getBind(CurrentUserResponse.CURRENT_USER_KEY);
                    if (currentUserResponse != null) {
                        Object val = func.apply(currentUserResponse);
                        setFieldValByName(field.getName(), val, metaObject);
                    }
                }
                , field -> field.isAnnotationPresent(clazz));
    }

}
