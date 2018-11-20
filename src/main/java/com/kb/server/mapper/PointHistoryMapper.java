package com.kb.server.mapper;

import com.kb.server.model.PointHistory;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface PointHistoryMapper {

    @Select("SELECT * FROM pointHistory")
    List<PointHistory> findAll();

    @Select("SELECT * FROM pointHistory WHERE pointIdx = #{pointIdx} ORDER BY time DESC LIMIT #{page},#{size}")
    List<PointHistory> findPointHistoriesByPointIdx(@Param("pointIdx") final int pointIdx,
                                                    @Param("page") final int page,
                                                    @Param("size") final int size);

    @Select("SELECT COUNT(pointHistoryIdx) FROM pointHistory WHERE pointIdx = #{pointIdx}")
    int countPointHistoriesByPointIdx(@Param("pointIdx") final int pointIdx);

    @Select("SELECT * FROM pointHistory WHERE pointIdx = #{pointIdx}, earn = #{earn}")
    List<PointHistory> findPointHistoriesByPointIdxAndIsEarn(@Param("pointIdx") final int pointIdx, @Param("earn") final boolean earn);

    @Select("SELECT * FROM pointHistory WHERE pointHistoryIdx = #{pointHistoryIdx}")
    PointHistory findByPointHistoryIdx(@Param("pointHistoryIdx") final int pointHistoryIdx);

    @Insert("INSERT INTO pointHistory(pointIdx, difference, comment, earn, time) VALUES(#{pointHistory.pointIdx}, #{pointHistory.difference}, #{pointHistory.comment}, #{pointHistory.earn}, NOW())")
    @Options(useGeneratedKeys = true, keyColumn = "pointHistoryIdx")
    int save(@Param("pointHistory") final PointHistory pointHistory);

    @Update("UPDATE pointHistory SET pointIdx = #{pointHistory.pointIdx}, difference = #{pointHistory.difference}, comment = #{pointHistory.comment}, earn = #{pointHistory.earn} WHERE pointHistoryIdx = #{pointHistoryIdx}")
    void update(@Param("pointHistoryIdx") final int pointHistoryIdx, @Param("pointHistory") final PointHistory pointHistory);

    @Delete("DELETE FROM pointHistory WHERE pointHistoryIdx = #{pointHistoryIdx}")
    void delete(@Param("pointHistoryIdx") final int pointHistoryIdx);
}
