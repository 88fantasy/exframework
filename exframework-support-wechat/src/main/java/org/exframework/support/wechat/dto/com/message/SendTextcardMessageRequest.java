package org.exframework.support.wechat.dto.com.message;

import org.springframework.lang.NonNull;
/**
 * @author rwe
 * @version 创建时间：Sep 21, 2020 11:23:16 AM 
 * 文本卡片消息请求
 */

public class SendTextcardMessageRequest extends SendMessageGlobalRequest  {

	@NonNull
	private Textcard textcard;

	public Textcard getTextcard() {
		return textcard;
	}

	public void setTextcard(Textcard textcard) {
		this.textcard = textcard;
	}

	/**
	 * 文本卡片内容
	 * @author pro
	 *
	 */
	public class Textcard {
		
		/**
		 * 标题
		 */
		@NonNull
		private String title;

		/**
		 * 描述
		 */
		@NonNull
		private String description;
		
		/**
		 * "点击后跳转的链接
		 */
		@NonNull
		private String url;
		
		/**
		 * 按钮文字
		 */
		private String btntxt;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getBtntxt() {
			return btntxt;
		}

		public void setBtntxt(String btntxt) {
			this.btntxt = btntxt;
		}
		

	}
}
