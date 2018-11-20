package com.kb.server.security.token;

import com.kb.server.dto.FormLoginDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by ehay@naver.com on 2018-10-09
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

public class PreAuthorizationToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizationToken(String userName, String password) {
        super(userName, password);
    }

    public PreAuthorizationToken(FormLoginDto dto) {
        super(dto.getId(), dto.getPassword());
    }

    public String getUserName(){
        return (String)super.getPrincipal();
    }

    public String getPassword(){
        return (String)super.getCredentials();
    }
}
