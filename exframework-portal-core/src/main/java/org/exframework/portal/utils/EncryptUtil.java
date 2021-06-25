package org.exframework.portal.utils;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class EncryptUtil {
	static private Log log = LogFactory.getLog(EncryptUtil.class.getName());

	public static String Encrypt(String strSrc, String encName) throws NoSuchAlgorithmException {
		java.security.MessageDigest digest = java.security.MessageDigest.getInstance(encName);
		byte messageDigest[] = digest.digest(strSrc.getBytes());
		// Create Hex String
		StringBuffer hexString = new StringBuffer();
		// 字节数组转换为 十六进制 数
		for (int i = 0; i < messageDigest.length; i++) {
			String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
			if (shaHex.length() < 2) {
				hexString.append(0);
			}
			hexString.append(shaHex);
		}
		return hexString.toString();
	}
}
