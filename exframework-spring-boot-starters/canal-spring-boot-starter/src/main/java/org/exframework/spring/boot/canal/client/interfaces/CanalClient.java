package org.exframework.spring.boot.canal.client.interfaces;

/**
 * canal 客户端接口层
 *
 * @author rwe
 * @date 2022/3/14 19:35
 */


public interface CanalClient {

    /**
     * 开启 canal 客户端，并根绝配置连接到 canal ,然后进行针对性的监听
     *
     * @param
     * @return
     */
    void start();
    
    
    /**
     * 关闭 canal 客户端
     *
     * 
     * @param
     * @return
     */
    void stop();
    
    /**
     * 判断 canal 客户端是否是开启状态
     *
     * 
     * @param
     * @return
     */
    boolean isRunning();
}
