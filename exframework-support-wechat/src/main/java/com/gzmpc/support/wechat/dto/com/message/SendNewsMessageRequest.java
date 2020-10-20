package com.gzmpc.support.wechat.dto.com.message;

import java.util.List;

import org.springframework.lang.NonNull;


/**
 * @author rwe
 * @version 创建时间：Sep 21, 2020 11:23:16 AM 
 * 图文消息请求
 */

public class SendNewsMessageRequest extends SendMessageGlobalRequest {

	/**
	 * 图文内容
	 */
	private News news;

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	/**
	 * 图文内容
	 * @author pro
	 *
	 */
	public class News {
		
		/**
		 * 图文消息，一个图文消息支持1到8条图文
		 */
		@NonNull
		private List<Articles> articles;

		public List<Articles> getArticles() {
			return articles;
		}

		public void setArticles(List<Articles> articles) {
			this.articles = articles;
		}
		
	}
	/**
	 * 文章内容
	 * @author pro
	 *
	 */
	public class Articles {
		
		/**
		 * 标题
		 */
		@NonNull
		private String title;
		
		/**
		 * 描述
		 */
		private String description;
		
		/**
		 * 点击后跳转的链接
		 */
		private String url;
		
		/**
		 * 图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图 1068*455，小图150*150
		 */
		private String picurl;

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

		public String getPicurl() {
			return picurl;
		}

		public void setPicurl(String picurl) {
			this.picurl = picurl;
		}
		
	}
}
