package com.kb.server.security.provider;

import com.kb.server.dto.UserDto;
import com.kb.server.security.AccountContext;
import com.kb.server.security.token.PostAuthorizationToken;
import com.kb.server.security.token.PreAuthorizationToken;
import com.kb.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * Created by ehay@naver.com on 2018-11-09
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        PreAuthorizationToken token = (PreAuthorizationToken) authentication;

        String userName = token.getUserName();
        String password = token.getPassword();

        UserDto userDto = userService.findById(userName);

        if (userDto == null) new NoSuchElementException("아이디에 맞는 계정이 없습니다.");

        if (isCorrectPassword(password, userDto)) {
            return PostAuthorizationToken.getTokenFromAccountContext(AccountContext.fromAccountModel(userDto));
        }

        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizationToken.class.isAssignableFrom(aClass);
    }

    private boolean isCorrectPassword(String password, UserDto account) {
        return passwordEncoder.matches(password, account.getPassword());
    }
}
