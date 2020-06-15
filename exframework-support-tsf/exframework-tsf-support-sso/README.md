## exframework

新框架-腾讯微服务TSF-单点登录
用于获得登录用户信息

事例
 @ApiOperation(value = "获取当前供应商企业信息")
 @RequestMapping(value = "/getCurrentSupplyerInfo", method = RequestMethod.GET)
 public ResponseResult getCurrentSupplyerInfo(@LoginUser(isFull=true,isAccount = true) LoginUserDto loginUser) {

isFull : 调用用户中心服务查询 用户基本信息
isAccount ：  调用用户中心服务查询 用户关联系账户
默认  isFull , isAccount  = false ,只返回用户UID 和基础角色；


.properties 文件配置以下参数
spring.userCenter.appSource=

指定查找关联系统的账号
spring.userCenter.appSource=SIP


Maven : exframework-tsf-support-sso