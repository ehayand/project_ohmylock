package com.kb.server.mapper;

import com.kb.server.model.CardType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface CardTypeMapper {

    @Select("SELECT * FROM cardType")
    List<CardType> findAll();

    @Select("SELECT * FROM cardType WHERE isCredit = #{isCredit}")
    List<CardType> findCardTypesByIsCredit(@Param("isCredit") final boolean isCredit);

    @Select("SELECT * FROM cardType WHERE cardTypeIdx = #{cardTypeIdx}")
    CardType findByCardTypeIdx(@Param("cardTypeIdx") final int cardTypeIdx);

    @Select("SELECT * FROM cardType WHERE name = #{name}")
    CardType findByName(@Param("name") final String name);

    @Insert("INSERT INTO cardType(isCredit, name, image) VALUES(#{cardType.isCredit}, #{cardType.name}, #{cardType.image})")
    @Options(useGeneratedKeys = true, keyColumn = "cardTypeIdx")
    int save(@Param("cardType") final CardType cardType);

    @Update("UPDATE cardType SET isCredit = #{cardType.isCredit}, name = #{cardType.name}, image = #{cardType.image} WHERE cardTypeIdx = #{cardTypeIdx}")
    void update(@Param("cardTypeIdx") final int cardTypeIdx, @Param("cardType") final CardType cardType);

    @Delete("DELETE FROM cardType WHERE cardTypeIdx = #{cardTypeIdx}")
    void delete(@Param("cardTypeIdx") final int cardTypeIdx);
}
