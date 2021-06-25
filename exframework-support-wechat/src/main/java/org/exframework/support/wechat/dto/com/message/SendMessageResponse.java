package org.exframework.support.wechat.dto.com.message;

import org.exframework.support.wechat.dto.GlobalResponse;

/**
* @author rwe
* @version 创建时间：Sep 21, 2020 10:53:20 AM
* 发送消息返回
* https://work.weixin.qq.com/api/doc/90000/90135/90236
*/

public class SendMessageResponse extends GlobalResponse {

	/**
	 * 无效的成员清单 userid1|userid2,
	 */
	private String invaliduser;
	
	/**
	 * 无效的部门清单 partyid1|partyid2
	 */
	private String invalidparty;
	
	/**
	 * 无效的标签清单  tagid1|tagid2
	 */
	private String invalidtag;

	public String getInvaliduser() {
		return invaliduser;
	}

	public void setInvaliduser(String invaliduser) {
		this.invaliduser = invaliduser;
	}

	public String getInvalidparty() {
		return invalidparty;
	}

	public void setInvalidparty(String invalidparty) {
		this.invalidparty = invalidparty;
	}

	public String getInvalidtag() {
		return invalidtag;
	}

	public void setInvalidtag(String invalidtag) {
		this.invalidtag = invalidtag;
	}
	
	
}
