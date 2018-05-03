package com.gzmpc.exframework.support.log.util;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;


/**
* @author rwe
* @version 创建时间：2018年3月7日 上午11:15:15
* 类说明
*/

@Component
public class ClientUtil {
	
	private final String otrsurl = "http://192.168.7.32/otrs/nph-genericinterface.pl/Webservice/Ticket/Ticket";

	public  String postJson(String json) {
		String result = null;
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			String url = otrsurl;
			httpclient = HttpClients.createDefault();
			HttpPost post = new HttpPost(url);

			post.setEntity(new StringEntity(json, Consts.UTF_8));
			response = httpclient.execute(post);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				result = EntityUtils.toString(entity, Consts.UTF_8);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					
				} else {
					
				}
			}
			EntityUtils.consume(entity);
		} catch (Exception e) {
			
		} finally {
			ClientUtil.closeQuietly(httpclient, response);
		}
		return result;
	}

	public static void closeQuietly(CloseableHttpClient client) {
		try {
			if (client != null) {
				client.close();
			}
		} catch (IOException e) {
			// quiet
		}
	}

	public static void closeQuietly(CloseableHttpResponse response) {
		try {
			if (response != null) {
				response.close();
			}
		} catch (IOException e) {
			// quiet
		}
	}

	public static void closeQuietly(CloseableHttpClient client, CloseableHttpResponse response) {
		closeQuietly(response);
		closeQuietly(client);
	}
}
