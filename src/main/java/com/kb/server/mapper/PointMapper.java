package com.kb.server.mapper;

import com.kb.server.model.Point;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface PointMapper {

    @Select("SELECT * FROM point")
    List<Point> findAll();

    @Select("SELECT * FROM point WHERE pointIdx = #{pointIdx}")
    Point findByPointIdx(@Param("pointIdx") final int pointIdx);

    @Select("SELECT * FROM point WHERE userIdx = #{userIdx}")
    Point findByUserIdx(@Param("userIdx") final int userIdx);

    @Insert("INSERT INTO point(userIdx, balance) VALUES(#{point.userIdx}, #{point.balance})")
    @Options(useGeneratedKeys = true, keyColumn = "pointIdx")
    int save(@Param("point") final Point point);

    @Update("UPDATE point SET userIdx = #{point.userIdx}, balance = #{point.balance} WHERE pointIdx = #{pointIdx}")
    void update(@Param("pointIdx") final int pointIdx, @Param("point") final Point point);

    @Delete("DELETE FROM point WHERE pointIdx = #{pointIdx}")
    void delete(@Param("pointIdx") final int pointIdx);
}
