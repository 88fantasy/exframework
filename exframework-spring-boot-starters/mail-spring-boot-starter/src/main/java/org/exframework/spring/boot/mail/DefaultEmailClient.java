package org.exframework.spring.boot.autoconfigure.mail;

import org.simplejavamail.api.email.AttachmentResource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;

import javax.activation.FileDataSource;
import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * COS 客户端
 *
 * @author rwe
 * @since Jan 1, 2021
 * <p>
 * Copyright @ 2021
 */

public class DefaultEmailClient implements EmailClient {

    private String from;

    private Mailer mailer;

    public DefaultEmailClient(String from, Mailer mailer) {
        this.from = from;
        this.mailer = mailer;
    }

    @Override
    public Mailer getMailer() {
        return mailer;
    }

    @Override
    public void sendEmail(Email email) {
        mailer.sendMail(email);
    }

    @Override
    public void sendEmail(Email email, boolean async) {
        mailer.sendMail(email, async);
    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        Email email = EmailBuilder.startingBlank()
                .from(null, from)
                .to(null, to)
                .withSubject(subject)
                .withHTMLText(content)
                .buildEmail();
        sendEmail(email);
    }

    @Override
    public void sendEmail(String to, String subject, String content, String[] attachments) {
        Email email = EmailBuilder.startingBlank()
                .from(null, from)
                .to(null, to)
                .withSubject(subject)
                .withHTMLText(content)
                .withAttachments(Arrays.asList(attachments).stream().map(attachment -> new AttachmentResource(attachment.substring(attachment.lastIndexOf(File.pathSeparator) + 1), new FileDataSource(attachment))).collect(Collectors.toList()))
                .buildEmail();
        sendEmail(email);
    }

    @Override
    public void sendEmail(String to, String subject, String content, File[] attachments) {
        Email email = EmailBuilder.startingBlank()
                .from(null, from)
                .to(null, to)
                .withSubject(subject)
                .withHTMLText(content)
                .withAttachments(Arrays.asList(attachments).stream().map(attachment -> new AttachmentResource(attachment.getName(), new FileDataSource(attachment))).collect(Collectors.toList()))
                .buildEmail();
        sendEmail(email);
    }
}
