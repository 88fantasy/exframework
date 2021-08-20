package org.exframework.spring.boot.autoconfigure.chinaunicom.sms;

import java.util.List;

/**
 *
 * @author rwe
 * @since 2021年6月17日
 *
 * Copyright @ 2021 
 * 
 */
public class EmptySmsClient implements SmsClient {


	@Override
	public SmsResponse send(SmsRequest request) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SmsResponse send(String to, String templateId, String message) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SmsResponse send(String to, String templateId, List<String> messages) {
		throw new UnsupportedOperationException();
	}

	@Override
	public SmsResponse send(String to, String templateId, String appId, List<String> messages) {
		throw new UnsupportedOperationException();
	}
}
