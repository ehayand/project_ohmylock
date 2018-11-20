package com.kb.server.security;

import com.kb.server.dto.UserDto;
import com.kb.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

/**
 * Created by ehay@naver.com on 2018-11-09
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Component
public class AccountContextService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        UserDto userDto = userService.findById(userId);

        if (userDto == null) new NoSuchElementException("아이디에 맞는 계정이 없습니다.");

        return getAccountContext(userDto);
    }

    private AccountContext getAccountContext(UserDto account) {
        return AccountContext.fromAccountModel(account);
    }
}
