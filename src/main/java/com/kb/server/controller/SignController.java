package com.kb.server.controller;

import com.kb.server.dto.UserDto;
import com.kb.server.model.DefaultRes;
import com.kb.server.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ehay@naver.com on 2018-11-16
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@RestController
public class SignController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public SignController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @param userDto
     * @return
     */
    @PostMapping("/signup")
    public DefaultRes<Map<String, Object>> signUp(@RequestBody final UserDto userDto) {
        Map<String, Object> response = new HashMap<>();

        if (userDto == null) return DefaultRes.res("FAILED", "NULL_USER");
        if (userDto.getId() == null) return DefaultRes.res("FAILED", "NULL_USER_ID");
        if (userDto.getName() == null) return DefaultRes.res("FAILED", "NULL_USER_NAME");
        if (userDto.getPassword() == null) return DefaultRes.res("FAILED", "NULL_USER_PASSWORD");
        if (userDto.getPhone() == null) return DefaultRes.res("FAILED", "NULL_USER_PHONE");
        if (userDto.getEmail() == null) return DefaultRes.res("FAILED", "NULL_USER_EMAIL");

        UserDto check = userService.findById(userDto.getId());

        if (check != null) return DefaultRes.res("FAILED", "DUPLICATE_ID");

        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);

        userDto.setRole("ROLE_USER");

        userService.save(userDto);
        response.put("name", userService.findById(userDto.getId()).getName());

        return DefaultRes.res("SUCCESS", response);
    }
}
