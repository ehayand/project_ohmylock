package com.kb.server.service;

import com.kb.server.mapper.AccountMapper;
import com.kb.server.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class AccountService {

    private final AccountMapper accountMapper;

    public AccountService(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    public List<Account> findAll() {
        return accountMapper.findAll();
    }

    public List<Account> findAccountsByBankIdx(final int bankIdx) {
        return accountMapper.findAccountsByBankIdx(bankIdx);
    }

    public List<Account> findAccountsByUserIdx(final int userIdx) {
        return accountMapper.findAccountsByUserIdx(userIdx);
    }

    public Account findByAccountIdx(final int accountIdx) {
        return accountMapper.findByAccountIdx(accountIdx);
    }

    public Account findByNumber(final Long number) {
        return accountMapper.findByNumber(number);
    }

    public int save(final Account account) {
        return accountMapper.save(account);
    }

    public void update(final int accountIdx, final Account account) {
        accountMapper.update(accountIdx, account);
    }

    public void delete(final int accountIdx) {
        accountMapper.delete(accountIdx);
    }
}
