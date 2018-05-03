package com.gzmpc.exframework.support.log;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.entity.ContentType;
import org.json.JSONObject;

import com.gzmpc.exframework.support.log.util.ClientUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    		ClientUtil util = new ClientUtil();
    		
    		JSONObject json = new JSONObject();
    		json.putOnce("UserLogin", "root@localhost");
    		json.putOnce("Password", "password@otrs.com");
    		Map<String,String> ticket = new HashMap<String,String>();
    		ticket.put("Title", "日志工单测试");
    		ticket.put("Queue", "产品列");
    		ticket.put("TypeID", "2"); // 默认为2 --事件类型
    		ticket.put("Service", "应用系统::官网");
    		ticket.put("Owner", "16369");
    		ticket.put("PriorityID",	 "3");
    		ticket.put("StateID", "1"); // 默认为1 -- 工单状态 新建
    		ticket.put("CustomerUser", "16369");
    		
    		json.put("Ticket", ticket);
    		
    		Map<String,String> article = new HashMap<String,String>();
    		article.put("Subject", "日志工单测试");
    		article.put("Body", "html内容");
    		article.put("ArticleTypeID", "8");
    		article.put("SenderTypeID", "3");
    		article.put("ContentType", "text/html; charset=utf8");
    		
    		json.put("Article", article);
    		System.out.println(json.toString());
    		String ret = util.postJson(json.toString());
    		System.out.println(ret);
    }
}
