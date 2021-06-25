package org.exframework.support.wechat.dto;


/**
 * @author rwe
 * @version 创建时间：Sep 21, 2020 10:54:21 AM 
 * 全局返回参数
 */

public class GlobalResponse {

	/**
	 * 出错返回码，为0表示成功，非0表示调用失败
	 */
	private Integer errcode;

	/**
	 * 返回码提示语
	 */
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		if( errcode == null) {
			this.errcode = 0;
		}
		else {
			this.errcode = errcode;
		}
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Boolean checkSuccess() {
		return errcode == null || errcode == 0;
	}
}
