package com.kb.server.controller;

import com.kb.server.security.token.PostAuthorizationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@RestController
@RequestMapping("/key")
public class MainController {

    @GetMapping("/hello")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getUserName(Authentication authentication) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;

        return token.getAccountContext().getUsername();
    }
}
