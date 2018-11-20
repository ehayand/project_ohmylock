package com.kb.server.controller;

import com.kb.server.dto.EmailDto;
import com.kb.server.dto.PasswordDto;
import com.kb.server.dto.PhoneDto;
import com.kb.server.dto.UserDto;
import com.kb.server.model.DefaultRes;
import com.kb.server.security.token.PostAuthorizationToken;
import com.kb.server.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ehay@naver.com on 2018-11-16
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@RestController
@RequestMapping("/key/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     *
     * @param authentication
     * @return
     */
    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> getUser(Authentication authentication) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String id = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(id);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");

        response.put("userIdx", userDto.getUserIdx());
        response.put("name", userDto.getName());
        response.put("id", userDto.getId());
        response.put("email", userDto.getEmail());
        response.put("phone", userDto.getPhone());

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param authentication
     * @param phoneDto
     * @return
     */
    @PostMapping("/update/phone")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> updatePhone(Authentication authentication,
                                           @RequestBody final PhoneDto phoneDto) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String userId = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(userId);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");
        if (phoneDto == null) return DefaultRes.res("FAILED", "NULL_PHONE");
        if (phoneDto.getNewPhone() == null) return DefaultRes.res("FAILED", "NULL_PHONE");

        userDto.setPhone(phoneDto.getNewPhone());
        userService.update(userDto.getUserIdx(), userDto);

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param authentication
     * @param emailDto
     * @return
     */
    @PostMapping("/update/email")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> updateEmail(Authentication authentication,
                                           @RequestBody final EmailDto emailDto) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String userId = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(userId);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");
        if (emailDto == null) return DefaultRes.res("FAILED", "NULL_EMAIL");
        if (emailDto.getNewEmail() == null) return DefaultRes.res("FAILED", "NULL_EMAIL");

        userDto.setEmail(emailDto.getNewEmail());
        userService.update(userDto.getUserIdx(), userDto);

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param authentication
     * @param passwordDto
     * @return
     */
    @PostMapping("/update/password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> updatePassword(Authentication authentication,
                                              @RequestBody final PasswordDto passwordDto) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String userId = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(userId);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");
//        if (passwordDto.getOldPassword() == null) return DefaultRes.res("FAILED", "NULL_PASSWORD");
        if (passwordDto.getNewPassword() == null) return DefaultRes.res("FAILED", "NULL_PASSWORD");

//        if (!passwordEncoder.matches(passwordDto.getOldPassword(), userDto.getPassword())) return DefaultRes.res("FAILED", "NOT_CORRECT_PASSWORD");
        String encodedPassword = passwordEncoder.encode(passwordDto.getNewPassword());
        userDto.setPassword(encodedPassword);

        userService.update(userDto.getUserIdx(), userDto);

        return DefaultRes.res("SUCCESS", response);
    }

    /**
     *
     * @param authentication
     * @return
     */
    @DeleteMapping("")
    @PreAuthorize("hasRole('ROLE_USER')")
    public DefaultRes<Map<String, Object>> deleteUser(Authentication authentication) {
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        String userId = token.getAccountContext().getUsername();

        Map<String, Object> response = new HashMap<>();

        UserDto userDto = userService.findById(userId);

        if (userDto == null) return DefaultRes.res("FAILED", "NOT_FOUND_USER");

        response.put("name", userDto.getName());
        /**
         *  회원 탈퇴 사용하지 않음..
         */
        // userService.delete(userDto.getUserIdx());

        return DefaultRes.res("SUCCESS", response);
    }

}
