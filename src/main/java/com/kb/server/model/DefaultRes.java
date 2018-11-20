package com.kb.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-17
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
@Builder
@AllArgsConstructor
public class DefaultRes<T> {

    private String message;
    private String error;
    private T data;

    public DefaultRes(final String message, final String error) {
        this.message = message;
        this.error = error;
        this.data = null;
    }

    public static <T> DefaultRes<T> res(final String message, final T t) { return res(message, "NOT_ERROR", t); }

    public static <T> DefaultRes<T> res(final String message, final String error) {
        return res(message, error, null);
    }

    public static <T> DefaultRes<T> res(final String message, final String error, final T t) {
        return DefaultRes.<T>builder()
                .data(t)
                .error(error)
                .message(message)
                .build();
    }

}
