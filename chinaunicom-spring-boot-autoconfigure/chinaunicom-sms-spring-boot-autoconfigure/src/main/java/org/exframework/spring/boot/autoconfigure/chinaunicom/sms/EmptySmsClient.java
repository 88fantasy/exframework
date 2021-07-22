package org.exframework.spring.boot.autoconfigure.chinaunicom.sms;

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
}
