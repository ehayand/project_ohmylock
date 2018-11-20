package com.kb.server.model;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-15
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class BalanceHistory {
    private int balanceHistoryIdx;
    private int historyIdx;
    private int accountIdx;
    private long balance;
}
