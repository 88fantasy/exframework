
# Rest

## 快速上手

提供对 Restful 格式的接口增强封装(仅限 Spring MVC), 目前支持以下特性:

- [x] 请求缓存 `WebContextHolder`

## 安装

```xml
<dependency>
    <groupId>org.exframework</groupId>
    <artifactId>exframework-support-rest</artifactId>
</dependency>
```



## 请求缓存

 参考 `RequestContextHolder` 实现的当前请求线程的缓存字段(由于程序可能会 request attribute,所以另起)

### 使用场景一
用于缓存当前请求中的用户信息,避免重复从数据库中加载

```java
import org.exframework.support.rest.context.WebContextHolder;

public class LoginService {

  private final Logger log = LoggerFactory.getLogger(this.getClass());

  private static final String REQUEST_CURRENT_USER_KEY = "currentUser";

  @Autowired
  UserService userService;

  @UniResearchGateWayContext
  public CurrentUserResponse currentUser() {
    CurrentUserResponse currentUserResponse = (CurrentUserResponse) WebContextHolder.getBind(REQUEST_CURRENT_USER_KEY);
    if (ObjectUtils.isEmpty(currentUserResponse)) {
      UniResearchGateWayContext.Context context = UniResearchGateWayContext.Context.get();
      if (context == null || !StrUtils.hasText(context.getUserId())) {
        log.error("获取帐号信息失败");
        throw new ApiException(ResultCode.UNAUTHORIZED.getCode(), "获取帐号信息失败");
      }
      currentUserResponse = userService.getUser(context.getUserId());
      WebContextHolder.bind(REQUEST_CURRENT_USER_KEY, currentUserResponse);
    }
    return currentUserResponse;
  }

}
```
