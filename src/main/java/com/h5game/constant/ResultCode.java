package com.h5game.constant;

/**
 * 服务响应码
 *
 * @author ASUS
 */
public enum ResultCode {
    /** 全局返回码，范围[90000,99999]begin */

    /**
     * 成功
     */
    SUCCESS("0", "成功"),
    /**
     * 失败
     */
    FAIL("-1", "失败"),
    URL_NOT_FOUND("2", "url不存在"),
    /**
     * 超时
     */
    TIMEOUT("1", "超时"),
    /**
     * 锁住
     */
    LOCK("3", "锁住"),
    /**
     * 重复
     */
    DUPLICATE("4", "重复"),
    /**
     * 系统异常
     */
    UNKNOWN("9999", "系统异常"),
    /**
     * 参数为空
     */
    PARAM_NULL("90000", "参数为空"),
    /**
     * 不合法的参数
     */
    PARAM_INVALID("90001", "不合法的参数"),
    /**
     * 不存在的对象
     */
    OBJECT_NO_EXISTS("90002", "不存在的对象"),
    /**
     * 日期过期
     */
    DATE_EXPIRE("90003", "无效的时间参数"),
    /**
     * 不合法的日期格式
     */
    DATE_FORMAT_INVALID("90004", "不合法的时间格式"),
    /**
     * 未指定 AccessToken
     */
    ACCESS_TOKEN_NULL("90005", "未指定AccessToken"),
    /**
     * AccessToken不存在
     */
    ACCESS_TOKEN_NO_EXISTS("90006", "AccessToken不存在"),
    /**
     * 无效的 AccessToken
     */
    ACCESS_TOKEN_INVALID("90007", "无效的 AccessToken"),
    /**
     * 请求没有签名
     */
    SIGN_NULL("90008", "请求没有签名"),
    /**
     * 签名校验失败
     */
    SIGN_VERIFY_FAIL("90009", "签名校验失败"),
    /**
     * 请求方法的应用级输入参数错误
     */
    REQUEST_PARAM_INVALID("90010", "请求方法的应用级输入参数错误"),
    /**
     * token密匙为空
     */
    TOKEN_SECRET_NULL("90011", "token密匙为空"),
    /**
     * token密匙不合法
     */
    TOKEN_SECRET_INVALID("90012", "token密匙不合法"),
    /**
     * 过期的 AccessToken
     */
    ACCESS_TOKEN_EXPIRE("90013", "过期的 AccessToken"),
    /**
     * JSON解析异常
     */
    JSON_ANALYZE_ERROR("90014", "JSON解析异常"),
    /**
     * 查询无返回结果
     */
    NO_RETURN_RESILT("90015", "查询无返回结果"),
    SITE_STOP("90016", "站点已被停用"),
    SITE_MACHINE("90017", "站点维护中"),

    USER_ONLINE("10000", "用户已登入"),
    /** 全局返回码end */

    /**
     * 查询无返回结果
     */
    USER_EMPTY_PAY_PASSWORD("10001", "用户支付密码为空"),
    /**
     * 账户已被冻结
     */
    USER_STATUS_FORZEN("10002", "账户已被冻结"),
    /**
     * 账户已被拉入黑名单
     */
    USER_STATUS_BLACKLIST("10003", "账户已被拉入黑名单！"),
    /**
     * 账户已被停用
     */
    USER_STATUS_DISABLE("10004", "账户已被停用!"),
    /**
     * 登陆过期
     */
    USER_LOGIN_OVERDUE("10005", "登陆过期!"),;

    private String key;
    private String message;

    ResultCode(String key, String message) {
        this.message = message;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

}
