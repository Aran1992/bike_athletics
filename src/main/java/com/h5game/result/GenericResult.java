package com.h5game.result;

import java.io.Serializable;

/**
 * @author qss
 */
public interface GenericResult<T> extends Serializable {
    /**
     * 返回响应数据对象
     *
     * @return 响应数据对象
     */
    T getResponse();

    /**
     * 返回响应信息
     *
     * @return 响应信息
     */
    String getMessage();

    /**
     * 返回响应信息简码
     *
     * @return
     */
    String getKey();
}
