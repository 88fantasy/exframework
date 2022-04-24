package org.exframework.gateway.sso;


/**
 * 常量
 */
public interface SsoConstants {

    String DEFAULT_DEVICE = "WEB";

    /*请求头*/
    /**
     * 存放token的key
     */
    String REQUEST_HEADER_TOKEN = "token";
    /**
     * 新生成token的key
     */
    String REQUEST_HEADER_NEW_TOKEN = "newToken";

    /*JWT*/
    /**
     * JWT生成的token中username的key
     */
    String JWT_TOKEN_USERNAME = "username";
    /**
     * JWT生成的token中用户信息的key
     */
    String JWT_TOKEN_USERINFO = "userInfo";
    /**
     * JWT生成的token中唯一标识的key
     */
    String JWT_TOKEN_SIGN = "sign";
    /**
     * JWT生成的token中來源的key
     */
    String JWT_TOKEN_SOURCE = "source";
    /**
     * JWT生成的token中其它用户的key
     */
    String JWT_TOKEN_OTHER_USER_SIGN = "otherUserSign";
    /**
     * JWT生成的token中其它用户的用户来源编码key
     */
    String JWT_TOKEN_OTHER_USER_SOURCECODE = "otherUserSourceCode";

    /**
     * JWT生成的用户登录标记key
     */
    String JWT_TOKEN_LOGIN_FLAG = "loginFlag";

    /*Redis*/
    /**
     * redis中缓存当前用户token的key前缀
     */
    String CACHE_TOKEN_PREFIX = "TOKEN_";
    /**
     * redis中缓存当前用户AUTHORIZATION_CODE的key前缀
     */
    String CACHE_AUTHORIZATION_CODE_PREFIX = "AUTHORIZATION_CODE_";
    String CACHE_AUTHORIZATION_CODE_LOCK_PREFIX = "AUTHORIZATION_CODE_LOCK";
    /**
     * redis中缓存验证码key前缀
     */
    String KEY_CAPTCHA_PREFIX = "CAPTCHA_";
    /**
     * redis中缓存登录失败次数key前缀
     */
    String LOGIN_FAILED_COUNT = "LOGIN_FAILED_COUNT_";

    /*授权*/
    /**
     * 授权客户端地址的key
     */
    String AUTHORIZATION_CLIENT_URL = "AUTHORIZATION_CLIENT_URL";
    /**
     * 授权Web页白名单key
     */
    String AUTHORIZATION_WHITELIST = "AUTHORIZATION_WHITELIST";
    /**
     * 授权秘钥key
     */
    String AUTHORIZATION_SECRET = "AUTHORIZATION_SECRET_";

    /*通用数字*/
    /**
     * 通用数字0
     */
    String NUMBER_ZERO = "0";
    /**
     * 通用数字1
     */
    String NUMBER_ONE = "1";

    /*分隔符*/
    /**
     * 逗号分隔符
     */
    String SPLIT_COMMA = ",";
    /**
     * 下划线分隔符
     */
    String SPLIT_UNDERLINE = "_";
    /**
     * 正向斜杆分隔符
     */
    String SPLIT_DIAGONAL = "/";
    /**
     * 管道分隔符
     */
    String SPLIT_PIPELINE = "\\|";

    /*JWT替换常量*/
    /**
     * 被替换值
     */
    String JWT = "J_W_T";
    /**
     * 替换值
     */
    String JWT_REPLACE = "--";

    /**
     * 应用白名单的key
     */
    String SYSTEM_WHITE = "SYSTEM_WHITE";

    /* CRLF过滤规则 */
    /**
     * 特殊格式
     */
    String CRLF_FILTER_RULE_SPECIAL_FORMAT = "\\s*|\t|\r|\n";
    /**
     * 特殊字符特
     */
    String CRLF_FILTER_RULE_SPECIAL_CHAR = "undefined|#|@|\\|<|>|(|)|$|%|*|;|~|!|-|+";

    /* 登录类型 */
    /**
     * 常规登录
     */
    String COMMON_LOGIN = "COMMON_LOGIN";
    /**
     * 常规登录（密码加密方式特殊）
     */
    String COMMON_SPECIAL_LOGIN = "COMMON_SPECIAL_LOGIN";
    /**
     * 短信登录
     */
    String SMS_LOGIN = "SMS_LOGIN";
    /**
     * 微信登录
     */
    String VX_LOGIN = "VX_LOGIN";
    /**
     * 微信其它情况登录
     */
    String VX_OTHER_LOGIN = "VX_OTHER_LOGIN";
    /**
     * 其它用户登录
     */
    String OTHER_USER_LOGIN = "OTHER_USER_LOGIN";

    String UNKNOWN = "unknown";

    String KEY_SALT_PREFIX = "SALT_";
}
