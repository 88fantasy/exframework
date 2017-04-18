package com.gzmpc.message.engine;

import com.gzmpc.message.MessageEngine;

import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.Required;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Session;

import java.util.Properties;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;
import javax.mail.Message;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import com.sun.mail.smtp.SMTPTransport;
import javax.mail.*;

/**
 * <p>
 * Title: 邮件消息引擎，负责发送和接收电子邮件
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company: Gzmpc
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class EmailEngine implements MessageEngine {
	static private Log log = LogFactory.getLog(EmailEngine.class.getName());

	// 邮件发送服务器smtp参数
	private String smtp;
	private String username ;
	private String password ;
	
	private String subjectSuffix;
	
	private Session session;
	
	@Required
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

	@Required
	public void setUsername(String username) {
		this.username = username;
	}

	@Required
	public void setPassword(String password) {
		this.password = password;
	}

	public void setSubjectSuffix(String subjectSuffix) {
		this.subjectSuffix = subjectSuffix;
	}

	public EmailEngine() {
		log.info("初始化邮件收发引擎。。。。。。");
		
		if(subjectSuffix == null) {
			subjectSuffix = "";
		}
		Properties mailProps = new Properties();
		mailProps.put("mail.smtp.host", smtp);
		mailProps.put("mail.smtp.auth", "true");
		session = Session.getInstance(mailProps, null);

		log.info("初始化邮件收发引擎完成！");
	}

	public void send(String to, String subject, String msg) throws MessagingException {
		if (session == null) {
			throw new MessagingException("邮件引擎没有初始化，不能发送邮件");
		}

		SMTPTransport t = null;

		try {
			MimeMessage newMessage = new MimeMessage(this.session);
			newMessage.setFrom(new InternetAddress(username));

			newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

			newMessage.setHeader("X-Mailer", "gzmpc");
			newMessage.setSubject(subject + subjectSuffix);
			newMessage.setSentDate(new Date());
			newMessage.setText(msg, "UTF-8", "html");

			t = (SMTPTransport) session.getTransport("smtp");

			t.connect(smtp, username, password);

			t.sendMessage(newMessage, newMessage.getAllRecipients());

		} catch (MessagingException ex) {
			log.error(ex.getMessage(), ex);
			throw ex;
		} finally {
			if (t != null) {
				try {
					t.close();
				} catch (MessagingException ex1) {
					throw ex1;
				}
			}
		}
	}

	public void send(String to, String subject, String msg, String[] attachment) throws MessagingException {
		if (session == null) {
			throw new MessagingException("邮件引擎没有初始化，不能发送邮件");
		}
		
		SMTPTransport t = null;

		try {
			MimeMessage newMessage = new MimeMessage(this.session);

			newMessage.setFrom(new InternetAddress(username));

			newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

			newMessage.setHeader("X-Mailer", "gzmpc");
			newMessage.setSubject(subject + subjectSuffix);
			newMessage.setSentDate(new Date());
			if (attachment == null) {
				newMessage.setText(msg, "UTF-8", "html");
			} else {
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setContent(msg, "text/html;charset=utf-8");
				mp.addBodyPart(mbp);
				for (int i = 0, j = attachment.length; i < j; i++) {
					if (attachment[i] != null && !"".equals(attachment[i])) {
						mbp = new MimeBodyPart();
						// filename=efile.nextElement().toString(); //选择出每一个附件名
						FileDataSource fds = new FileDataSource(attachment[i]); // 得到数据源
						mbp.setDataHandler(new DataHandler(fds)); // 得到附件本身并至入BodyPart
						mbp.setFileName(MimeUtility.encodeText(fds.getName(), "utf-8", null)); // 得到文件名同样至入BodyPart

						mp.addBodyPart(mbp);
					}
				}

				newMessage.setContent(mp); // Multipart加入到信件
				// 发送信件
				newMessage.saveChanges();
			}

			t = (SMTPTransport) session.getTransport("smtp");

			t.connect(smtp, username, password);

			t.sendMessage(newMessage, newMessage.getAllRecipients());

		} catch (MessagingException ex) {
			log.error(ex.getMessage(), ex);
			throw ex;
		} catch (UnsupportedEncodingException ex) {
			log.error(ex.getMessage(), ex);
			throw new MessagingException("Filename  UnsupportedEncoding UTF8");
		} finally {
			if (t != null) {
				try {
					t.close();
				} catch (MessagingException ex1) {
					throw ex1;
				}
			}
		}
	}

	public void send(String to, String subject, String msg, ByteArrayDataSource[] attachment, String[] filename)
			throws MessagingException {
		if (session == null) {
			throw new MessagingException("邮件引擎没有初始化，不能发送邮件");
		}

		SMTPTransport t = null;

		try {
			MimeMessage newMessage = new MimeMessage(this.session);

			newMessage.setFrom(new InternetAddress(username));

			newMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));

			newMessage.setHeader("X-Mailer", "gzmpc");
			newMessage.setSubject(subject + " -- [电子商务平台邮件发送,请勿回复]");
			newMessage.setSentDate(new Date());
			if (attachment == null) {
				newMessage.setText(msg, "UTF-8", "html");
			} else {

				// ByteArrayDataSource(byte[] data, String type)
				// Create a ByteArrayDataSource with data from the specified
				// byte array and with the specified MIME type.
				// ByteArrayDataSource(InputStream is, String type)
				// Create a ByteArrayDataSource with data from the specified
				// InputStream and with the specified MIME type.
				// ByteArrayDataSource(String data, String type)
				// Create a ByteArrayDataSource with data from the specified
				// String and with the specified MIME type.
				Multipart mp = new MimeMultipart();
				MimeBodyPart mbp = new MimeBodyPart();
				mbp.setContent(msg, "text/html;charset=utf-8");
				mp.addBodyPart(mbp);
				for (int i = 0, j = attachment.length; i < j; i++) {
					if (attachment[i] != null) {
						mbp = new MimeBodyPart();
						mbp.setDataHandler(new DataHandler(attachment[i])); // 得到附件本身并至入BodyPart
						mbp.setFileName(filename[i]); // 得到文件名同样至入BodyPart
						mp.addBodyPart(mbp);
					}
				}

				newMessage.setContent(mp); // Multipart加入到信件
				// 发送信件
				newMessage.saveChanges();
			}

			t = (SMTPTransport) session.getTransport("smtp");

			t.connect(smtp, username, password);

			t.sendMessage(newMessage, newMessage.getAllRecipients());

		} catch (MessagingException ex) {
			log.error(ex.getMessage(), ex);
			throw ex;
		} finally {
			if (t != null) {
				try {
					t.close();
				} catch (MessagingException ex1) {
					throw ex1;
				}
			}
		}
	}

	public static String getISOFileName(Part body) {
		// 设置一个标志，判断文件名从Content-Disposition中获取还是从Content-Type中获取
		boolean flag = true;
		if (body == null) {
			return null;
		}
		String[] cdis;
		try {
			cdis = body.getHeader("Content-Disposition");
		} catch (MessagingException e) {
			return null;
		}
		if (cdis == null) {
			flag = false;
		}
		if (!flag) {
			try {
				cdis = body.getHeader("Content-Type");
			} catch (MessagingException e) {
				return null;
			}
		}
		if (cdis == null) {
			return null;
		}
		if (cdis[0] == null) {
			return null;
		}
		// 从Content-Disposition中获取文件名
		if (flag) {
			int pos = cdis[0].indexOf("filename=");
			if (pos < 0) {
				return null;
			}
			// 如果文件名带引号
			if (cdis[0].charAt(cdis[0].length() - 1) == '"') {
				return cdis[0].substring(pos + 10, cdis[0].length() - 1);
			}
			return cdis[0].substring(pos + 9, cdis[0].length());
		} else {
			int pos = cdis[0].indexOf("name=");
			if (pos < 0) {
				return null;
			}
			// 如果文件名带引号
			if (cdis[0].charAt(cdis[0].length() - 1) == '"') {
				return cdis[0].substring(pos + 6, cdis[0].length() - 1);
			}
			return cdis[0].substring(pos + 5, cdis[0].length());
		}
	}

}