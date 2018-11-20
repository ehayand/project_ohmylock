package com.kb.server.mapper;

import com.kb.server.model.BalanceHistory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by ehay@naver.com on 2018-11-15
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface BalanceHistoryMapper {

    @Select("SELECT * FROM balanceHistory WHERE historyIdx = #{accountHistoryIdx} AND accountIdx = #{accountIdx}")
    BalanceHistory findByAccountHistoryIdxAndAccountIdx(@Param("accountHistoryIdx") final int accountHistoryIdx,
                                                        @Param("accountIdx") final int accountIdx);
}
