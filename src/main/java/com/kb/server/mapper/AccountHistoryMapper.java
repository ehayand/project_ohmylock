package com.kb.server.mapper;

import com.kb.server.model.AccountHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface AccountHistoryMapper {

    @Select("SELECT * FROM accountHistory")
    List<AccountHistory> findAll();

    @Select("SELECT * FROM accountHistory WHERE sendAccountIdx = #{sendAccountIdx}")
    List<AccountHistory> findAccountHistoriesBySendAccountIdx(@Param("sendAccountIdx") final int sendAccountIdx);

    @Select("SELECT * FROM accountHistory WHERE receiveAccountIdx = #{receiveAccountIdx}")
    List<AccountHistory> findAccountHistoriesByReceiveAccountIdx(@Param("receiveAccountIdx") final int receiveAccountIdx);

    @Select("SELECT * FROM accountHistory WHERE sendAccountIdx = #{accountIdx} OR receiveAccountIdx = #{accountIdx} ORDER BY time DESC LIMIT #{page},#{size}")
    List<AccountHistory> findAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(@Param("accountIdx") final int accountIdx,
                                                                                 @Param("page") final int page,
                                                                                 @Param("size") final int size);

    @Select("SELECT COUNT(accountHistoryIdx) FROM accountHistory WHERE sendAccountIdx = #{accountIdx} OR receiveAccountIdx = #{accountIdx}")
    int countAccountHistoriesBySendAccountIdxOrReceiveAccountIdx(@Param("accountIdx") final int accountIdx);

    @Select("SELECT * FROM accountHistory WHERE sendAccountIdx = #{sendAccountIdx} AND receiveAccountIdx = #{receiveAccountIdx} ORDER BY time DESC")
    List<AccountHistory> findAccountHistoriesBySendAccountIdxAndReceiveAccountIdx(@Param("sendAccountIdx") final int sendAccountIdx, @Param("receiveAccountIdx") final int receiveAccountIdx);

    @Select("SELECT * FROM accountHistory WHERE accountHistoryIdx = #{accountHistoryIdx}")
    AccountHistory findByAccountHistoryIdx(@Param("accountHistoryIdx") final int accountHistoryIdx);

    @Insert("INSERT INTO (sendAccountIdx, receiveAccountIdx, difference, time) VALUES(#{sendAccountIdx}, #{receiveAccountIdx}, #{difference}, NOW())")
    @Options(useGeneratedKeys = true, keyColumn = "")
    int save(@Param("sendAccountIdx") final int sendAccountIdx, @Param("receiveAccountIdx") final int receiveAccountIdx, @Param("difference") final Long difference);

    @Update("UPDATE accountHistory SET sendAccountIdx = #{accountHistory.sendAccountIdx}, receiveAccountIdx = #{accountHistory.receiveAccountIdx}, difference = #{accountHistory.difference} WHERE accountHistoryIdx = #{accountHistoryIdx}")
    void update(@Param("accountHistoryIdx") final int accountHistoryIdx, @Param("accountHistory") final AccountHistory accountHistory);

    @Delete("DELETE FROM accountHistory WHERE accountHistoryIdx = #{accountHistoryIdx}")
    void delete(@Param("accountHistoryIdx") final int accountHistoryIdx);
}
