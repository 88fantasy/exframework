package com.gzmpc.support.wechat.dto.com.message;

import org.springframework.lang.NonNull;

/**
 * @author rwe
 * @version 创建时间：Sep 21, 2020 11:23:16 AM 
 * 文本消息请求
 */

public class SendTextMessageRequest extends SendMessageGlobalRequest  {

	/**
	 * 文本消息内容
	 */
	@NonNull
	private Text text;
	
	/**
	 * 是否是保密消息
	 */
	private Integer safe;
	

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

	/**
	 * 文本消息内容
	 * @author pro
	 *
	 */
	public class Text {
		
		/**
		 * 文本消息
		 */
		@NonNull
		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
		
	}
}
