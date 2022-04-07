## Exframework

Exframework 旨在为项目快速开发提供一系列的基础能力，方便用户根据项目需求快速进行功能拓展

## 技术架构

### 开发环境

- 语言：Java 11

- IDE(JAVA)： IDEA

- IDE(前端)： VSCODE

- 依赖管理：Maven

- 数据库：MySQL5.7+  &  Oracle 11g & Mongo4+

- 缓存：Redis

### 后端

- 基础框架：Spring Boot 2.6.6

- 微服务框架： Spring Cloud Alibaba 2021.1

- 持久层框架：Mybatis-plus 3.4.3.1

- 安全框架：sureness

- 微服务技术栈：Spring Cloud Alibaba、Nacos、Gateway、Sentinel、Skywalking

- 数据库连接池：阿里巴巴Druid 1.2.6

- 缓存框架：redis

- 日志打印：logback

- 其他：jackson，easyexcel，Knife4j(swagger)，quartz, 等。

### 前端

- [React](https://zh-hans.reactjs.org/)
- [Ant Design](https://ant.design/) - UI 基础组件库
- [Ant Design Pro](https://pro.ant.design/) - 页面级 UI 组件库
- [Umijs](https://umijs.org/) - react 前端应用框架
- [Qiankun](https://qiankun.umijs.org/zh) - 微前端框架
- [ant-design-exframework](https://www.npmjs.com/package/ant-design-exframework) 自研 UI 组件库
- [@antv/g2](https://antv.alipay.com/zh-cn/index.html) - Alipay AntV 数据可视化图表
- eslint

## 项目结构

```code
.
|-- exframework-spring-cloud-alibaba   -- 针对 阿里云 spring cloud alibaba 体系的相关适配
|   |-- exframework-alibaba-ms-quickstart    -- 基于 alibaba 的后端项目脚手架
|   `-- exframework-spring-cloud-gateway-native-quickstart  -- 基于 scg 的标准工程脚手架(redis路由)
|
|-- exframework-spring-cloud-tsf  -- 针对 腾讯云 TSF 体系的相关适配
|   |-- exframework-tsf-gateway-quickstart    -- 基于 scg 的标准工程脚手架(腾讯云控制台路由)
|   `-- exframework-tsf-microservice-quickstart  -- 基于 腾讯云tsf 的后端项目脚手架
|
|
|-- exframework-portal   -- 前后端结合相关
|   |-- exframework-portal-admin    -- 管理后台组件
|   |-- exframework-portal-core    -- 核心组件
|   |-- exframework-portal-jdbc  --  基于 jdbc 的内核实现
|   |-- exframework-portal-web    -- 对外接口组件
|   |-- exframework-portal-web-jdbc  -- 基于 jdbc 的界外接口实现
|
|-- exframework-support-extends   -- 扩展模块，大多是对于一些第三方组件的扩展增强
|   |-- exframework-support-common   --  基础依赖, 最通用的扩展
|   |-- exframework-support-doc   -- 基于  swagger 相关的一些扩展,主要用于生成开发文档
|   |-- exframework-support-jdbc   -- 基于 jdbc 模式相关的一些扩展,主要扩展 mybatis-plus
|   |-- exframework-support-jersey   -- 早期对于 rest(jersey)实现方式的扩展
|   |-- exframework-support-job  --  定时任务一些操作扩展,主要基于 quartz
|   |-- exframework-support-monitor   --  针对监控接口的处理, 用于统一提供对外监控接口
|   |-- exframework-support-rest  -- 针对restful 接口的增强封装(仅兼容spring mvc)
|   |-- exframework-support-rest-base  -- 针对restful 接口的增强封装(兼容 mvc和webflux)
|   |-- exframework-support-soap  -- 针对 SOAP (Xml) 的支持
|   `-- exframework-support-wechat  -- 针对微信相关的一些支持
|
|-- exframework-spring-boot-starters        -- 对于各种能力的增强 starter
|   |-- canal-spring-boot-starter -- canal 同步工具 集成工具
|   |-- cos-spring-boot-starter -- 腾讯云对象存储 Cos 集成工具
|   |-- docker-spring-boot-starter --  docker registry 集成工具
|   |-- pulsar-spring-boot-starter -- Pulsar 消息队列 集成工具
|   |-- elasticsearch-spring-boot-starter -- Es 集成工具
|   |-- redis-spring-boot-starter -- Redis 集成工具
|   |-- mongodb-spring-boot-starter -- MongoDB 集成工具
|   |-- minio-spring-boot-starter -- 对象存储 MinIO 集成工具, 同时支持S3
|   |-- developer-spring-boot-starter  -- 开发者中心集成工具
|   `-- mail-spring-boot-starter -- 邮件发送
|
|-- chinaunicom-spring-boot-starters    -- 针对联通产互的适配和扩展
|   |-- chinaunicom-rest-spring-boot-starter rest 接口的风格适配和增强
|   |-- chinaunicom-sms-spring-boot-starter 发送短信的能力(支持 http 代理)
|
|
`-- docs -- 文档网站

```
