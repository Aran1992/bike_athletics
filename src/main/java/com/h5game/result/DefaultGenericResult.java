package com.h5game.result;

/**
 * @param <T>
 * @author ASUS
 */
@SuppressWarnings("serial")
public class DefaultGenericResult<T> implements GenericResult<T> {
    private final T response;

    private final String message;

    private final String key;

    public DefaultGenericResult(String key) {
        this(key, null, null);
    }

    public DefaultGenericResult(String key, String message) {
        this(key, message, null);
    }

    public DefaultGenericResult(String key, T response) {
        this(key, null, response);
    }

    public DefaultGenericResult(String key, String message, T response) {
        this.key = key;
        this.message = message;
        this.response = response;
    }

    @Override
    public T getResponse() {
        return response;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getKey() {
        return key;
    }
}
