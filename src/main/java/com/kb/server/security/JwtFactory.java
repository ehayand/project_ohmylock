package com.kb.server.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * Created by ehay@naver.com on 2018-11-09
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Component
public class JwtFactory {

    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);

    private static String signingKey = "slidekey";

    public String generateToken(AccountContext context) {

        String token = null;

        try {
            token = JWT.create()
                    .withIssuer("key_slide")
                    .withClaim("USERNAME", context.getAccount().getId())
                    .withClaim("USER_ROLE", context.getAccount().getRole())
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return token;
    }

    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        return Algorithm.HMAC256(signingKey);
    }

}
