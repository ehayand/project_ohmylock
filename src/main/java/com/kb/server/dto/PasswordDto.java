package com.kb.server.dto;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-14
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class PasswordDto {
    private String oldPassword;
    private String newPassword;
}
