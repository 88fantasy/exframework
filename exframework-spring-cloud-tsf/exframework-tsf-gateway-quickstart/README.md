# exframework

新框架-Spring Cloud Gateway (网关路由)-脚手架

Maven : exframework-tsf-gateway-quickstart

##  实现方式

在原生 scgw 基础上通过 `腾讯云控制台` 微服务网关分组配置 进行动态路由配置
关联内部 Api 需要在 `腾讯云控制台` 进行操作


## 使用方式

https://cloud.tencent.com/document/product/649/40198

## 注意

本脚手架基于 tsf 实现, 如果需要通过系统路由配置功能使用请移步至  exframework-tsf-gateway-native-quickstart

>本地运行需要设置 TSF 信息
-Dtsf_token=111
-Dtsf_namespace_id=ggggga
-Dtsf_application_id=aaaaaa
-Dtsf_app_id=111
-Dtsf_group_id=group-1

