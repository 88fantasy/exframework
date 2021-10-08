package org.exframework.portal.metadata.sys;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.exframework.portal.metadata.di.DataItemEntity;
import org.exframework.portal.metadata.di.DataItemRef;
import org.exframework.portal.metadata.entity.EntityClass;

@EntityClass
public class Logger {
	
	/**
	 * 主键
	 */
	@DataItemEntity(value = "loggerId", name = "日志id")
	private Long loggerId;
	
	/**
	 * 模块code
	 */
	@DataItemRef("code")
	private String moduleCode;
	
	/**
	 * 日志内容
	 */
	@NotEmpty
	@DataItemEntity(value = "content", name = "内容")
	private String content;
	
	/**
	 * 相关业务单据
	 */
	@DataItemEntity(value = "sourceId", name = "相关业务单据")
	private String sourceId;
	
	/**
	 * 操作参数(用于记录当时上下文)
	 */
	@DataItemEntity(value = "param", name = "操作参数")
	private String param;
	
	/**
	 * 帐号
	 */
	@DataItemRef(value = "accountId")
	private String account;
	
	/**
	 * 帐号名称
	 */
	@DataItemRef(value = "accountName")
	private String accountName;
	
	/**
	 * 创建时间
	 */
	private Date credate;


	public Long getLoggerId() {
		return loggerId;
	}

	public void setLoggerId(Long loggerId) {
		this.loggerId = loggerId;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Date getCredate() {
		return credate;
	}

	public void setCredate(Date credate) {
		this.credate = credate;
	}

}