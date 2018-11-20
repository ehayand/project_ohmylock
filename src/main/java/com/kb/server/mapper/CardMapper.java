package com.kb.server.mapper;

import com.kb.server.model.Card;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface CardMapper {

    @Select("SELECT * FROM card")
    List<Card> findAll();

    @Select("SELECT * FROM card WHERE userIdx = #{userIdx} ORDER BY cardIdx ASC")
    List<Card> findCardsByUserIdx(@Param("userIdx") final int userIdx);

    @Select("SELECT * FROM card WHERE userIdx = #{userIdx} ORDER BY cardIdx ASC LIMIT #{page},#{size}")
    List<Card> findCardsByUserIdxPaging(@Param("userIdx") final int userIdx,
                                        @Param("page") final int page,
                                        @Param("size") final int size);

    @Select("SELECT COUNT(cardIdx) FROM card WHERE userIdx = #{userIdx}")
    int countCardsByUserIdx(@Param("userIdx") final int userIdx);

    @Select("SELECT * FROM card WHERE accountIdx = #{accountIdx}")
    List<Card> findCardsByAccountIdx(@Param("accountIdx") final int accountIdx);

    @Select("SELECT * FROM card WHERE cardTypeIdx = #{cardTypeIdx}")
    List<Card> findCardsByCardTypeIdx(@Param("cardTypeIdx") final int cardTypeIdx);

    @Select("SELECT * FROM card WHERE cardIdx = #{cardIdx}")
    Card findByCardIdx(@Param("cardIdx") final int cardIdx);

    @Select("SELECT * FROM card WHERE number = #{number}")
    Card findByNumber(@Param("number") final Long number);

    @Insert("INSERT INTO card(userIdx, accountIdx, cardTypeIdx, number, cardLimit) VALUES(#{card.userIdx}, #{card.accountIdx}, #{card.cardTypeIdx}, #{card.number}, #{card.cardLimit})")
    @Options(useGeneratedKeys = true, keyColumn = "cardIdx")
    int save(@Param("card") final Card card);

    @Update("UPDATE card SET userIdx = #{card.userIdx}, accountIdx = #{card.accountIdx}, cardTypeIdx = #{card.cardTypeIdx}, number = #{card.number}, cardLimit = #{card.cardLimit} WHERE cardIdx = #{cardIdx}")
    void update(@Param("cardIdx") final int cardIdx, @Param("card") final Card card);

    @Delete("DELETE FROM card WHERE cardIdx = #{cardIdx}")
    void delete(@Param("cardIdx") final int cardIdx);

}
