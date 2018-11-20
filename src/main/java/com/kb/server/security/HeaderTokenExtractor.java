package com.kb.server.security;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Component
public class HeaderTokenExtractor {

    public static final String HEADER_PREFIX = "Bearer ";

    public String extract(String header) {

        if (StringUtils.isEmpty(header) | header.length() < HEADER_PREFIX.length()) {
            throw new InvalidJwtException("올바른 토큰 정보가 아닙니다.");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
}
