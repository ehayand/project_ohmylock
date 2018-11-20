package com.kb.server.service;

import com.kb.server.mapper.AccountHistoryMapper;
import com.kb.server.model.AccountHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class AccountHistoryService {

    private final AccountHistoryMapper accountHistoryMapper;

    public AccountHistoryService(AccountHistoryMapper accountHistoryMapper) {
        this.accountHistoryMapper = accountHistoryMapper;
    }

    public List<AccountHistory> findAll() {
        return accountHistoryMapper.findAll();
    }

    public List<AccountHistory> findAccountHistoriesBySendAccountIdx(final int sendAccountIdx) {
        return accountHistoryMapper.findAccountHistoriesBySendAccountIdx(sendAccountIdx);
    }

    public List<AccountHistory> findAccountHistoriesByReceiveAccountIdx(final int receiveAccountIdx) {
        return accountHistoryMapper.findAccountHistoriesByReceiveAccountIdx(receiveAccountIdx);
    }

    public List<AccountHistory> findAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(final int accountIdx, final int page, final int size) {
        return accountHistoryMapper.findAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(accountIdx, page, size);

    }

    public int countAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(final int accountIdx) {
        return accountHistoryMapper.countAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(accountIdx);
    }

    public List<AccountHistory> findAccountHistoriesBySendAccountIdxAndReceiveAccountIdx(final int sendAccountIdx, final int receiveAccountIdx) {
        return accountHistoryMapper.findAccountHistoriesBySendAccountIdxAndReceiveAccountIdx(sendAccountIdx, receiveAccountIdx);
    }

    public AccountHistory findByAccountHistoryIdx(final int accountHistoryIdx) {
        return accountHistoryMapper.findByAccountHistoryIdx(accountHistoryIdx);
    }

    public int save(final int sendAccountIdx, final int receiveAccountIdx, final Long difference) {
        return accountHistoryMapper.save(sendAccountIdx, receiveAccountIdx, difference);
    }

    public void update(final int accountHistoryIdx, final AccountHistory accountHistory) {
        accountHistoryMapper.update(accountHistoryIdx, accountHistory);
    }

    public void delete(final int accountHistoryIdx) {
        accountHistoryMapper.delete(accountHistoryIdx);
    }
}
