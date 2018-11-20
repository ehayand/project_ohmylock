package com.kb.server.mapper;

import com.kb.server.model.Account;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface AccountMapper {

    @Select("SELECT * FROM account")
    List<Account> findAll();

    @Select("SELECT * FROM account WHERE bankIdx = #{bankIdx}")
    List<Account> findAccountsByBankIdx(@Param("bankIdx") final int bankIdx);

    @Select("SELECT * FROM account WHERE userIdx = #{userIdx}")
    List<Account> findAccountsByUserIdx(@Param("userIdx") final int userIdx);

    @Select("SELECT * FROM account WHERE number = #{number}")
    Account findByNumber(@Param("number") final Long number);

    @Select("SELECT * FROM account WHERE accountIdx = #{accountIdx}")
    Account findByAccountIdx(@Param("accountIdx") final int accountIdx);

    @Insert("INSERT INTO account(bankIdx, userIdx, number, balance) VALUES(#{account.bankIdx}, #{account.userIdx}, #{account.number}, 0)")
    @Options(useGeneratedKeys = true, keyColumn = "account.accountIdx")
    int save(@Param("account") final Account account);

    @Update("UPDATE account SET balance = #{account.balance} WHERE accountIdx = #{accountIdx}")
    void update(@Param("accountIdx") final int accountIdx, @Param("account") final Account account);

    @Delete("DELETE FROM account WHERE accountIdx = #{accountIdx}")
    void delete(@Param("accountIdx") final int accountIdx);

}
