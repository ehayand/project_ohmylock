package com.kb.server.security;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

public class InvalidJwtException extends RuntimeException {

    public InvalidJwtException(String msg) {
        super(msg);
    }

}
