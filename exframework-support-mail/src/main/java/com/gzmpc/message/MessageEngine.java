package com.gzmpc.message;

import javax.mail.MessagingException;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2016</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public interface MessageEngine {
  /**
   * 消息发送函数
   * @param to       接收地址
   * @param subject  消息主题（对于SMS消息，subject可以为空）
   * @param msg      消息主体
   * @return         
   */
  public void send(String to,String subject,String msg) throws MessagingException;
}