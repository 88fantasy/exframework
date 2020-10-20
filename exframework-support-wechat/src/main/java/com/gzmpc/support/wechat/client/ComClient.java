package com.gzmpc.support.wechat.client;

import com.dtflys.forest.annotation.BaseRequest;
import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;
import com.gzmpc.support.wechat.constant.WeChatComApiConstants;
import com.gzmpc.support.wechat.dto.com.message.SendImageMessageRequest;
import com.gzmpc.support.wechat.dto.com.message.SendMessageResponse;
import com.gzmpc.support.wechat.dto.com.message.SendNewsMessageRequest;
import com.gzmpc.support.wechat.dto.com.message.SendTextMessageRequest;
import com.gzmpc.support.wechat.dto.com.message.SendTextcardMessageRequest;
import com.gzmpc.support.wechat.exception.ApiException;

/**
* @author rwe
* @version 创建时间：Sep 23, 2020 11:07:28 PM
* 类说明
*/

@BaseRequest(
    baseURL = "https://wechat-api.gzmpc.com",     // 默认域名
    contentType = "application/json"
)
public interface ComClient {

	/**
	 * 发送文本信息
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@Post(url = WeChatComApiConstants.WECHAT_COM_API_SEND_TEXT, dataType = "json")
	public SendMessageResponse sendText(@Body SendTextMessageRequest request) throws ApiException;
	
	/**
	 * 发送文本卡片消息
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@Post(url = WeChatComApiConstants.WECHAT_COM_API_SEND_TEXTCARD, dataType = "json")
	public SendMessageResponse sendTextcard(@Body SendTextcardMessageRequest request) throws ApiException;
	
	/**
	 * 发送图片消息
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@Post(url = WeChatComApiConstants.WECHAT_COM_API_SEND_IMAGE, dataType = "json")
	public SendMessageResponse sendImage(@Body SendImageMessageRequest request) throws ApiException;
	
	/**
	 * 发送图文消息
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@Post(url = WeChatComApiConstants.WECHAT_COM_API_SEND_NEWS, dataType = "json")
	public SendMessageResponse sendNews(@Body SendNewsMessageRequest request) throws ApiException;
}
