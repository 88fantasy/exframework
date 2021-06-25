package org.exframework.support.wechat.dto.com.message;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


/**
 * @author rwe
 * @version 创建时间：Sep 21, 2020 11:23:16 AM 
 * 图片消息请求
 */

public class SendImageMessageRequest extends SendMessageGlobalRequest {

	/**
	 * 图片内容
	 */
	@NonNull
	private Image image;
	/**
	 * 是否是保密消息
	 */
	@Nullable
	private Integer safe;

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

	/**
	 * 图片内容
	 * @author pro
	 *
	 */
	public class Image {
		
		/**
		 * 图片媒体文件id
		 */
		@NonNull
		private String mediaId;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}

	}
}
