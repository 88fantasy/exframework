package org.exframework.spring.boot.autoconfigure.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;

import static org.exframework.spring.boot.autoconfigure.mail.MailProperties.DEFAULT_PREFIX;

/**
 *
 * @author rwe
 * @since 2021年5月20日
 *
 * Copyright @ 2021 
 * 
 */
@ConfigurationProperties(value = DEFAULT_PREFIX)
public class MailProperties {

	/**
     * PREFIX
     */
    public static final String DEFAULT_PREFIX = "spring.mail";

	/**
	 * SMTP server host. For instance, `smtp.example.com`.
	 */
	private String host;

	/**
	 * SMTP server port.
	 */
	private Integer port;

	/**
	 * Login user of the SMTP server.
	 */
	private String username;

	/**
	 * Login password of the SMTP server.
	 */
	private String password;

	/**
	 * socks proxy used to connect
	 */
	private Proxy proxy;


	public String getHost() {
		return host;
	}

	public MailProperties setHost(String host) {
		this.host = host;
		return this;
	}

	public Integer getPort() {
		return port;
	}

	public MailProperties setPort(Integer port) {
		this.port = port;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public MailProperties setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public MailProperties setPassword(String password) {
		this.password = password;
		return this;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public MailProperties setProxy(Proxy proxy) {
		this.proxy = proxy;
		return this;
	}
}
