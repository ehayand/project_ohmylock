package com.kb.server.service;

import com.kb.server.mapper.BankMapper;
import com.kb.server.model.Bank;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class BankService {

    private final BankMapper bankMapper;

    public BankService(BankMapper bankMapper) {
        this.bankMapper = bankMapper;
    }

    public List<Bank> findAll() {
        return bankMapper.findAll();
    }

    public Bank findByBankIdx(final int bankIdx) {
        return bankMapper.findByBankIdx(bankIdx);
    }

    public Bank findByName(final String name) {
        return bankMapper.findByName(name);
    }

    public int save(final Bank bank) {
        return bankMapper.save(bank);
    }

    public void update(final int bankIdx, final Bank bank) {
        bankMapper.update(bankIdx, bank);
    }

    public void delete(final int bankIdx) {
        bankMapper.delete(bankIdx);
    }
}
