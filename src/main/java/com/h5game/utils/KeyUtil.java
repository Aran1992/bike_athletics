package com.h5game.utils;

import java.util.UUID;

/**
 * @author qss
 * @date 2018/12/2
 */
public class KeyUtil {
    public static synchronized String createId() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }
}
