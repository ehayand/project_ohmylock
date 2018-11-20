package com.kb.server.dto;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class UserDto {
    private int userIdx;
    private String name;
    private String id;
    private String password;
    private String email;
    private String phone;
    private String role;
}
