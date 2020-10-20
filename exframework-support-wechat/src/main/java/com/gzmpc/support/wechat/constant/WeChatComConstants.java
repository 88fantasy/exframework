package com.gzmpc.support.wechat.constant;

/**
 * 常量类
 * @author pro
 *
 */
public interface WeChatComConstants {
	
	public final static String WECHAT_COM_TOKEN_BASE = "/wechat-sign/com/token/{0}/{1}";

	public final static String WECHAT_COM_API_BASE = "https://qyapi.weixin.qq.com/cgi-bin";
	
	public final static String WECHAT_COM_API_TOKEN = WECHAT_COM_API_BASE+"/gettoken?corpid=${id}&corpsecret=${secret}";
	
	public final static String WECHAT_COM_API_MESSAGE = "/message/send";
	
	
}
