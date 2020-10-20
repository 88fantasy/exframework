package com.gzmpc.support.wechat.enums;

/**
 * @author rwe
 * @version 创建时间：Sep 21, 2020 2:08:38 PM 
 * 企业微信 发送消息 枚举类
 */

public enum WeChatSendMessageEnum {

	TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), FILE("file"), TEXTCARD("textcard"), NEWS("news"),
	MPNEWS("mpnews"), MARKDOWN("markdown"), MINIPROGRAM("miniprogram_notice"), TASKCARD("taskcard");

	private String msgtype;

	WeChatSendMessageEnum(String msgtype) {
		this.msgtype = msgtype;
	}

	public String getMsgtype() {
		return msgtype;
	}

}
