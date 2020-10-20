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



### 第三步： 代码使用

####方式一 同步静态类方式   

通过ClientBuilder创建客户端 即可使用里面的方法

```java


    public String  test() {
        ComClient comClient = ClientBuilder.comClient();
        SendTextMessageRequest request = new SendTextMessageRequest();
        request.setTouser("工号");
        request.setAgentid(应用 id);
        Text text = request.new Text();
        text.setContent("测试");
        request.setText(text);
        return comClient.sendText(request).toString();
    }

```

#### 方式二 通过注入的方式

1.先在 springboot 启动类添加 forest 注解

```java

@SpringBootApplication
@Configuration
@ForestScan(basePackages = "com.gzmpc.support.wechat.client")
public class MyApp {
 ...
}

```

2.然后便能在其他代码中从 Spring 上下文注入接口实例，然后如调用普通接口那样调用即可

```java

@Service
public class ParamController {

	@Autowired
	ComClient comClient;

    public String  test() {
    
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
