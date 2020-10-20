# 微信组件

组件介绍：
-------------------------------------

目前支持企业微信的消息推送


极速开始
-------------------------------------
以下例子基于Spring Boot

### 第一步：添加maven 库地址

```xml
    <repositories>
        <repository>
            <id>gzmpc</id>
            <name>Gzmpc Repository</name>
            <url>http://maven.gzmpc.com/repository/maven-public/</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
            <releases>
                <enabled>true</enabled>
            </releases>
        </repository>
    </repositories>
```

### 第二步：添加Maven依赖

直接添加以下maven依赖即可

```xml
<dependency>
    <groupId>com.gzmpc.exframework</groupId>
    <artifactId>exframework-support-wechat</artifactId>
    <version>0.4.2</version>
</dependency>
```



### 第三步：创建一个`class`

就以高德地图API为栗子吧

```java

package com.gzmpc.microservice.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dtflys.forest.config.ForestConfiguration;
import com.gzmpc.microservice.config.constant.ConfigParamApiConstants;
import com.gzmpc.microservice.config.dto.ParamSaveDTO;
import com.gzmpc.microservice.config.service.ParamService;
import com.gzmpc.microservice.wechat.dto.com.message.SendTextMessageRequest;
import com.gzmpc.microservice.wechat.dto.com.message.SendTextMessageRequest.Text;
import com.gzmpc.microservice.wechat.client.ClientBuilder;
import com.gzmpc.microservice.wechat.client.ComClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 控制类
 * @author pro
 *
 */
public class ParamController {

    public String  test() {
    /**
    * 通过ClientBuilder创建客户端 即可使用里面的方法
    */
        ComClient comClient = ClientBuilder.comClient();
        SendTextMessageRequest request = new SendTextMessageRequest();
        request.setTouser("工号");
        request.setAgentid(应用 id);
        Text text = request.new Text();
        text.setContent("测试");
        request.setText(text);
        return comClient.sendText(request).toString();
    }
}

```

