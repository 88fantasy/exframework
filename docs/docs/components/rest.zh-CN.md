
# Rest

## 快速上手

提供对Restful 格式的接口增强封装, 同时定义标准返回类以统一返回格式,  目前支持以下特性:

- [x] 请求实体注解 `EnumValue`、`RequestFieldChecker`,  返回实体注解`Data`
- [x] 全局错误处理 `GlobalControllerExceptionControllerAdvice`
- [x] 标准返回格式 `ApiResponse`、`ApiResponseData<T>`、`ApiResponsePage<T>`

## 安装

```xml
<dependency>
    <groupId>org.exframework</groupId>
    <artifactId>exframework-support-rest</artifactId>
</dependency>
```

## 注解

### @EnumValue

支持对枚举类型进行友好错误提示和值范围校验

|  属性   |    类型    | 必须指定 | 默认值 | 描述       |
| :-----: | :--------: | :------: | :----: | ---------- |
| message |  `String`  |    否    |   ""   | 提示信息   |
|  anyOf  | `String[]` |    否    |   []   | 限定范围值 |

使用示例

```java
import org.exframework.support.rest.annotation.EnumValue;

public class Request  {

    @NotNull
    @ApiModelProperty("xxxx")
    @EnumValue
    private ProjectTeamEnum.PlanType plan;

}
```

***

### @RequestFieldChecker

支持对入参进行逻辑校验, 目前主要用于主键有效性校验

|   属性    |                类型                 | 必须指定 |              默认值              | 描述                                      |
| :-------: | :---------------------------------: | :------: | :------------------------------: | ----------------------------------------- |
|   value   |               `Class`               |    是    |                                  | 校验类 必须实现 `Function<Object,String>` |
|  message  |              `String`               |    否    |                ""                | 提示信息                                  |
| exception | `Class<? extends RuntimeException>` |    否    | `ConstraintDeclarationException` | 限定范围值                                |

使用示例, 先定义校验类

```java
@Component
public class ProjectIdChecker implements Function<Object, String> {

    @Autowired
    ProjectService projectService;

    @Override
    public String apply(Object o) {
        String projectId = (String) o;
        try {
            UniresearchProjectInfo info = projectService.validProjectId(projectId);
            return null;
        } catch ( NotFoundException e) {
            return e.getMessage();
        }
    }


}
```

```java
import org.exframework.support.rest.annotation.RequestFieldChecker;

public class Request  {

    @ApiModelProperty("xxxx")
    @RequestFieldChecker(ProjectIdChecker.class)
    private String id;

}
```

也可以直接用于`controller`函数

```java
import org.exframework.support.rest.annotation.RequestFieldChecker;

@RestController
public class Controller  {

    @ApiOperation(value = "xxxx")
    @GetMapping(value = "/xxxxx", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChinaUnicomApiResponseList<ProjectTeamListWithVisitsResponse> teamAndVisits(@ApiParam(value = "项目主键", required = true) @RequestFieldChecker(ProjectIdChecker.class) @PathVariable(value = "projectId") String projectId) {
        return new ChinaUnicomApiResponseList<>(projectService.teamAndVisits(projectId));
    }

}
```

***

### @Data

自动对返回值进行`ApiResponseData<T>`的封装

使用示例

```java
import org.exframework.support.rest.annotation.Data;

@RestController
public class Controller  {

    @Data
    @ApiOperation(value = "xxxx")
    @GetMapping(value = "/xxxxx", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response request() {
        return new Response();
    }

}
```

## 全局错误处理

通过拦截对全局请求返回进行错误处理, 并封装成标准报文返回

- `MethodArgumentNotValidException` 参数校验错误
- `ApiException` 前端请求错误
- `ServerException` 服务端运行错误
- `HttpMessageNotReadableException` 请求参数错误(非JSON格式)
- `NullPointerException` 空指针
- `IOException` IO 错误
- `InvalidFormatException` JSON格式错误
- `Exception` 全局错误

## 返回值规范

后端返回报文应当按照统一的格式,以便前端进行统一处理

### 状态码

| 一级分类                | 二级分类 | 状态                  | 说明                                                         |
| ----------------------- | -------- | --------------------- | ------------------------------------------------------------ |
| 2XX请求已接收并成功处理 | 200      | OK                    | 服务器端成功返回用户请求的数据                               |
|                         | 201      | CREATED               | 用户新建或修改数据成功（Post/Put/patch）                     |  |
|                         | 202      | Accepted              | 表示一个请求已经进入后台排队(异步处理)                       |  |
|                         | 204      | NO CONTENT            | 用户删除数据成功(Delete)                                     |  |
| 3XX重定向               |          |                       |                                                              |
| 4XX请求端错误           | 400      | Bad Request           | 用户发出的请求有错误，服务器不理解客户端的请求，未做任何处理 |
|                         | 401      | Unauthorized          | 表示用户没有权限(未登录,未认证)                              |  |
|                         | 403      | Forbidden             | 表示用户已登录，但是没有权限,访问被禁止了                    |  |
|                         | 404      | Not Found             | 所请求的资源不存在，或不可用                                 |  |
|                         | 405      | Method Not Allowed    | HTTP方法错误                                                 |  |
|                         | 406      | Not Acceptable        | 用户的请求的格式错误(content-type)                           |  |
|                         | 429      | Too Many Requests     | 客户端的请求次数超过限额                                     |  |
| 5XX服务端错误           | 500      | INTERNAL SERVER ERROR | 服务器处理请求发生错误                                       |
|                         | 502      | Bad Gateway           | 网关错误                                                     |
|                         | 503      | Service Unavailable   | 服务不可用,无法处理请求                                      |
|                         | 504      | Gateway timeout       | 网关超时                                                     |

### 数据格式

普通返回标准实体 `ApiResponseData`
|  属性  |      类型       |                                        描述                                         |
| :----: | :-------------: | :---------------------------------------------------------------------------------: |
|  code  |      `int`      |                                  http响应的状态码                                   |
| status |    `boolean`    | 快速进行判断,HTTP状态响应码在400-499或500-599之间为 `false`, 其他一般都为 `success` |
|  data  | `Object\|Array` |                                      数据信息                                       |

```json
{
    "code": 200,
    "status": true,
    "data": [{
        "userName": "tugenhua",
        "age": 31
    }]
}
```

分页返回标准实体 `ApiResponsePage<T>`
|  属性  |      类型      |                                        描述                                         |
| :----: | :------------: | :---------------------------------------------------------------------------------: |
|  code  |     `int`      |                                  http响应的状态码                                   |
| status |   `boolean`    | 快速进行判断,HTTP状态响应码在400-499或500-599之间为 `false`, 其他一般都为 `success` |
|  data  | `PageModel<T>` |                                      分页数据                                       |

`PageModel<T>`
| 属性  |   类型    |   描述   |
| :---: | :-------: | :------: |
| pager |  `Pager`  | 分页信息 |
| list  | `List<T>` | 数据列表 |

`Pager`
|   属性   |  类型  |   描述   |
| :------: | :----: | :------: |
|   size   | `long` |  总页数  |
|  total   | `long` |   总数   |
| current  | `long` |  当前页  |
| pageSize | `long` | 每页条数 |

```json
{
    "code": 200,
    "status": true,
    "data": {
      "pager": {
        "size":20,
        "total":30,
        "current":1,
        "pageSize":20
      },
      "list": [{
        "userName": "tugenhua",
        "age": 31
      }]
}
```

错误返回
|  属性   |        类型        |                                        描述                                         |
| :-----: | :----------------: | :---------------------------------------------------------------------------------: |
|  code   |       `int`        |                                  http响应的状态码                                   |
| status  |     `boolean`      | 快速进行判断,HTTP状态响应码在400-499或500-599之间为 `false`, 其他一般都为 `success` |
|  data   | `String\|String[]` |                              针对开发或运维的错误信息                               |
| message |      `String`      |                                 针对用户的可读信息                                  |

```json
{
    "code": 401,
    "status": false,
    "message": "用户没有权限",
    "data": null
}
```