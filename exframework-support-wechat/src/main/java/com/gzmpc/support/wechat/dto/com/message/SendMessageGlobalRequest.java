package com.gzmpc.support.wechat.dto.com.message;

import org.springframework.lang.NonNull;

/**
 * @author rwe
 * @version 创建时间：Sep 21, 2020 2:17:09 PM 
 * 企业微信 发送消息 通用类
 */

public class SendMessageGlobalRequest {
	
	/**
	 * 成员ID列表（消息接收者，多个接收者用‘|’分隔，最多支持1000个）。特殊情况：指定为@all，则向关注该企业应用的全部成员发送
	 */
	private String touser;

	/**
	 * 部门ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	 */
	private String toparty;

	/**
	 * 标签ID列表，多个接收者用‘|’分隔，最多支持100个。当touser为@all时忽略本参数
	 */
	private String totag;
	
	/**
	 * 企业应用的id，整型。企业内部开发，可在应用的设置页面查看；第三方服务商，可通过接口 获取企业授权信息(https://work.weixin.qq.com/api/doc/90000/90135/90236#10975/%E8%8E%B7%E5%8F%96%E4%BC%81%E4%B8%9A%E6%8E%88%E6%9D%83%E4%BF%A1%E6%81%AF) 获取该参数值
	 */
	@NonNull
	private Integer agentid;
	
	/**
	 * 表示是否开启id转译，0表示否，1表示是，默认0
	 */
	private Integer enableIdTrans;
	
	/**
	 * 表示是否开启重复消息检查，0表示否，1表示是，默认0
	 */
	private Integer enableDuplicateCheck;

	/**
	 * 表示是否重复消息检查的时间间隔，默认1800s，最大不超过4小时
	 */
	private Integer duplicateCheckInterval;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getToparty() {
		return toparty;
	}

	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	public String getTotag() {
		return totag;
	}

	public void setTotag(String totag) {
		this.totag = totag;
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public Integer getEnableIdTrans() {
		return enableIdTrans;
	}

	public void setEnableIdTrans(Integer enableIdTrans) {
		this.enableIdTrans = enableIdTrans;
	}

	public Integer getEnableDuplicateCheck() {
		return enableDuplicateCheck;
	}

	public void setEnableDuplicateCheck(Integer enableDuplicateCheck) {
		this.enableDuplicateCheck = enableDuplicateCheck;
	}

	public Integer getDuplicateCheckInterval() {
		return duplicateCheckInterval;
	}

	public void setDuplicateCheckInterval(Integer duplicateCheckInterval) {
		this.duplicateCheckInterval = duplicateCheckInterval;
	}

}
