package org.exframework.spring.boot.canal.client.exception;

/**
 * canal 操作的异常类
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */
public class CanalClientException extends RuntimeException {
    
    /**
     * 默认构造方法
     * 
     * @param
     * @return
     */
    public CanalClientException() {
    }
    /**
     *  带错误信息的构造方法
     * 
     * @param message
     * @return
     */
    public CanalClientException(String message) {
        super(message);
    }
    
    /**
     * 带错误信息和其造成原因的构造方法
     * 
     * @param  message
     * @param cause
     * @return
     */
    public CanalClientException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     * 带造成错误信息的构造方法
     * 
     * @param cause
     * @return
     */
    public CanalClientException(Throwable cause) {
        super(cause);
    }
    
    /**
     * 
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     * @return
     */
    public CanalClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
