package com.kb.server.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by ehay@naver.com on 2018-10-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

public class JwtPreProcessingToken extends UsernamePasswordAuthenticationToken {

    private JwtPreProcessingToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JwtPreProcessingToken(String token){
        this(token, token.length());
    }
}
