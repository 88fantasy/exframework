package org.exframework.support.sso.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.exframework.support.sso.core.constant.SecurityConstants;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * @author
 * @version
 */

public class HttpClientUtils {

	private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

	public static JSONObject postJsonRetObject(String url, String authorization, JSON json) throws RestClientException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		JSONObject result = null;
		if (null != json && !StringUtils.isEmpty(url)) {
			String jsonStr = json.toJSONString();
			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON_UTF8);
			header.add(SecurityConstants.AUTHORIZATION,authorization);
			HttpEntity<String> httpEntity = new HttpEntity<String>(jsonStr, header);

			try {
				HttpComponentsClientHttpRequestFactory requestFactory = getFactory();
				ResponseEntity<String> response = new RestTemplate(requestFactory).exchange(url, HttpMethod.POST,
						httpEntity, String.class);
				if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
					result = JSON.parseObject(response.getBody());
				} else {

				}
			} catch (RestClientException e) {
				logger.error("post请求提交失败:" + url + "  , 提交 json : " + jsonStr, e);
				throw e;
			}
		}

		return result;
	}

	public static JSONArray getJsonRetArray(String url) throws RestClientException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		JSONArray result = null;
		if (!StringUtils.isEmpty(url)) {
			try {
				HttpComponentsClientHttpRequestFactory requestFactory = getFactory();
				ResponseEntity<String> response = new RestTemplate(requestFactory).exchange(url, HttpMethod.GET, null,
						String.class);
				if (response.getStatusCodeValue() == HttpStatus.OK.value()) {
					result = JSON.parseArray(response.getBody());
				} else {

				}
			} catch (RestClientException e) {
				logger.error("get请求获取失败:" + url, e);
				throw e;
			}
		}

		return result;
	}

	public static HttpComponentsClientHttpRequestFactory getFactory()
			throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		TrustStrategy acceptingTrustStrategy = (cert, authType) -> true;
		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);

		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("https", sslsf).register("http", new PlainConnectionSocketFactory()).build();

		BasicHttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager(
				socketFactoryRegistry);
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf)
				.setConnectionManager(connectionManager).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		requestFactory.setConnectTimeout(2000);
		requestFactory.setConnectionRequestTimeout(2000);
		requestFactory.setHttpClient(httpClient);
		return requestFactory;
	}
}
