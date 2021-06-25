# exframework

## XXX应用

 微服务定位：
-------------------------------------
XXXXXX


极速开始
-------------------------------------
以下例子基于Spring Boot

### 第一步：添加Maven依赖

直接添加以下maven依赖即可

```xml
<dependency>
    <groupId>com.gzmpc.microservice</groupId>
    <artifactId>microservice-xxx-core</artifactId>
    <version>最新版请查看pom</version>
</dependency>
```



### 第二步： 注入`proxy`进行调用,相关代理类在包体 `com.gzmpc.microservice.xxx.proxy` 下

```java

@Service
public class ParamController {

  @Autowired
  LocalProxy localProxy;

    public void  test() {
    
        localProxy.xxxx();
    }
}

```