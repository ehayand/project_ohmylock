package com.kb.server.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class AccountHistory {
    private int accountHistoryIdx;
    private int sendAccountIdx;
    private int receiveAccountIdx;
    private long difference;
    private LocalDateTime time;
}
