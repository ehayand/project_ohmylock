package com.kb.server.service;

import com.kb.server.mapper.BalanceHistoryMapper;
import com.kb.server.model.BalanceHistory;
import org.springframework.stereotype.Service;

/**
 * Created by ehay@naver.com on 2018-11-15
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class BalanceHistoryService {

    private final BalanceHistoryMapper balanceHistoryMapper;

    public BalanceHistoryService(BalanceHistoryMapper balanceHistoryMapper) {
        this.balanceHistoryMapper = balanceHistoryMapper;
    }

    public BalanceHistory findByAccountHistoryIdxAndAccountIdx(final int accountHistoryIdx, final int accountIdx) {
        return balanceHistoryMapper.findByAccountHistoryIdxAndAccountIdx(accountHistoryIdx, accountIdx);
    }
}
