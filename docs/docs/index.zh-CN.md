---
hero:
  title: Expert Framework
  desc: 开箱即用的Java后端开发框架
  actions:
    - text: 快速上手
      link: /getting-started
features:
  - icon: https://gw.alipayobjects.com/zos/bmw-prod/881dc458-f20b-407b-947a-95104b5ec82b/k79dm8ih_w144_h144.png
    title: 组件
    desc: 可插拔组件库
  - icon: https://gw.alipayobjects.com/zos/bmw-prod/d60657df-0822-4631-9d7c-e7a869c2f21c/k79dmz3q_w126_h126.png
    title: 单体应用
    desc: 单体应用脚手架
  - icon: https://gw.alipayobjects.com/zos/bmw-prod/d1ee0c6f-5aed-4a45-a507-339a4bfe076c/k7bjsocq_w144_h144.png
    title: 微服务
    desc: 分布式应用脚手架
footer: Open-source MIT Licensed | Copyright © 2021<br />Powered by [rwe]
---

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

- 基础框架：Spring Boot 2.4.8.RELEASE

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
|   |-- exframework-support-elasticsearch   -- 基于 elasticsearch 相关的一些扩展
|   |-- exframework-support-jdbc   -- 基于 jdbc 模式相关的一些扩展,主要扩展 mybatis-plus
|   |-- exframework-support-jersey   -- 早期对于 rest(jersey)实现方式的扩展
|   |-- exframework-support-job  --  定时任务一些操作扩展,主要基于 quartz
|   |-- exframework-support-mongo  -- 基于 mongo 数据库的一些扩展
|   |-- exframework-support-monitor   --  针对监控接口的处理, 用于统一提供对外监控接口
|   |-- exframework-support-rest  -- 针对restful 接口的增强封装
|   |-- exframework-support-resis   -- 基于 spring redis 和 redission 的扩展功能
|   |-- exframework-support-soap  -- 针对微信支付的一些操作封装
|   |-- exframework-support-pulsar  --  基于 分布式消息 pulsar 的一些扩展增强
|   `-- exframework-support-wechat  -- 针对微信相关的一些支持
|
|-- exframework-spring-boot-starters        -- 对于各种能力的增强 starter
|   |-- cos-spring-boot-starter -- 腾讯云对象存储 Cos 集成工具
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
