package org.exframework.support.common.entity;

/**
 * 检查结果
 *
 * @author rwe
 * @date 2021/7/16 17:54
 **/
public class CheckerResult<T> {

    private T object;

    private String message;

    public CheckerResult(T object) {
       this(object, null);
    }

    public CheckerResult(String message) {
        this(null,message);
    }

    public CheckerResult(T object, String message) {
        this.object = object;
        this.message = message;
    }

    public T getObject() {
        return object;
    }

    public CheckerResult setObject(T object) {
        this.object = object;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CheckerResult setMessage(String message) {
        this.message = message;
        return this;
    }
}
