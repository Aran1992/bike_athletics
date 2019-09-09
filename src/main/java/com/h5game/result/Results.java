package com.h5game.result;

import com.h5game.constant.ResultCode;

/**
 * 服务调用返回结果工具类
 *
 * @author ASUS
 */
@SuppressWarnings("rawtypes")
public class Results {
    /**
     * 返回未知错误
     *
     * @return 服务调用结果
     */
    public static GenericResult unknown() {
        return new DefaultGenericResult(ResultCode.UNKNOWN.getKey(), ResultCode.UNKNOWN.getMessage());
    }

    /**
     * 返回错误信息
     * <p>
     * 响应码
     *
     * @return 服务调用结果
     */
    public static GenericResult fail() {
        return new DefaultGenericResult(ResultCode.FAIL.getKey(), ResultCode.FAIL.getMessage());
    }

    /**
     * 返回错误信息
     * <p>
     * 响应码
     *
     * @return 服务调用结果
     */
    public static GenericResult fail(String message) {
        return new DefaultGenericResult(ResultCode.FAIL.getKey(), message);
    }

    /**
     * 返回错误信息
     * <p>
     * 响应码
     *
     * @return 服务调用结果
     */
    public static <T> GenericResult<T> fail(String message, T response) {
        return new DefaultGenericResult(ResultCode.FAIL.getKey(), message, response);
    }

    /**
     * 返回错误信息
     * <p>
     * 响应码
     *
     * @return 服务调用结果
     */
    public static GenericResult fail(ResultCode code) {
        return new DefaultGenericResult(code.getKey(), code.getMessage());
    }

    /**
     * 返回成功信息
     *
     * @return 服务调用结果
     */
    public static GenericResult success() {
        return new DefaultGenericResult(ResultCode.SUCCESS.getKey(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 返回成功信息
     *
     * @param message 自定义信息
     * @return 服务调用结果
     */
    public static GenericResult success(String message) {
        return new DefaultGenericResult(ResultCode.SUCCESS.getKey(), message);
    }

    /**
     * 返回成功信息
     *
     * @param response 响应数据
     * @return 服务调用结果
     */
    public static <T> GenericResult<T> success(T response) {
        return new DefaultGenericResult<T>(ResultCode.SUCCESS.getKey(), response);
    }

    /**
     * 返回成功信息
     *
     * @param message  自定义信息
     * @param response 响应数据
     * @return 服务调用结果
     */
    public static <T> GenericResult<T> success(String message, T response) {
        return new DefaultGenericResult<T>(ResultCode.SUCCESS.getKey(), message, response);
    }

    public static <T> GenericResult<T> custom(String key, String message, T response) {
        return new DefaultGenericResult<T>(key, message, response);
    }

    public static <T> GenericResult<T> custom(String key, String message) {
        return new DefaultGenericResult<T>(key, message, null);
    }

    public static <T> GenericResult<T> custom(ResultCode code) {
        return new DefaultGenericResult<T>(code.getKey(), code.getMessage(), null);
    }

    public static <T> GenericResult<T> custom(ResultCode code, String message) {
        return new DefaultGenericResult<T>(code.getKey(), message, null);
    }

}
