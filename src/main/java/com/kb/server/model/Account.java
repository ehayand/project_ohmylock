package com.kb.server.model;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class Account {
    private int accountIdx;
    private int bankIdx;
    private int userIdx;
    private long number;
    private long balance;
}
