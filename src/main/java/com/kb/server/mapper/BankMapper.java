package com.kb.server.mapper;

import com.kb.server.model.Bank;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface BankMapper {

    @Select("SELECT * FROM bank")
    List<Bank> findAll();

    @Select("SELECT * FROM bank WHERE bankIdx = #{bankIdx}")
    Bank findByBankIdx(@Param("bankIdx") final int bankIdx);

    @Select("SELECT * FROM bank WHERE name = #{name}")
    Bank findByName(@Param("name") final String name);

    @Insert("INSERT INTO bank(name) VALUES(#{bank.name})")
    @Options(useGeneratedKeys = true, keyColumn = "bankIdx")
    int save(@Param("bank") final Bank bank);

    @Update("UPDATE bank SET name = #{bank.name} WHERE bankIdx = #{bankIdx}")
    void update(@Param("bankIdx") final int bankIdx, @Param("bank") final Bank bank);

    @Delete("DELETE FROM bank WHERE bankIdx = #{bankIdx}")
    void delete(@Param("bankIdx") final int bankIdx);
}
