package org.exframework.spring.boot.mail;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;

import java.io.File;

/**
 *
 * @author rwe
 * @since 2021年6月17日
 *
 * Copyright @ 2021 
 * 
 */
public interface EmailClient {

    Mailer getMailer();

    /**
     * 发送邮件
     * @param email 邮件实体
     */
    void sendEmail(Email email);

    /**
     * 发送邮件
     * @param email 邮件实体
     * @param async 是否异步
     */
    void sendEmail(Email email, boolean async);

    /**
     * 发送邮件
     * @param to 目标地址
     * @param subject 标题
     * @param content 内容(HTML)
     */
    void sendEmail(String to, String subject, String content);

    /**
     * 发送邮件
     * @param to 目标地址
     * @param subject 标题
     * @param content 内容(HTML)
     * @param attachments 附件
     */
    void sendEmail(String to, String subject, String content, String[] attachments);

    /**
     * 发送邮件
     * @param to 目标地址
     * @param subject 标题
     * @param content 内容(HTML)
     * @param attachments 附件
     */
    void sendEmail(String to, String subject, String content, File[] attachments);


}
