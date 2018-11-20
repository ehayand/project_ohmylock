package com.kb.server.mapper;

import com.kb.server.model.LockAdvert;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-12
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface LockAdvertMapper {

    @Select("SELECT * FROM lockAdvert ORDER BY lockAdvertIdx ASC LIMIT #{page},#{size}")
    List<LockAdvert> findAll(@Param("page") final int page,
                             @Param("size") final int size);

    @Select("SELECT COUNT(lockAdvertIdx) FROM lockAdvert")
    int countAll();

    @Select("SELECT * FROM lockAdvert WHERE title = #{title}")
    List<LockAdvert> findLockAdvertsByAdvertiser(@Param("title") final String title);

    @Select("SELECT * FROM lockAdvert WHERE lockAdvertIdx = #{lockAdvertIdx}")
    LockAdvert findByLockAdvertIdx(@Param("lockAdvertIdx") final int lockAdvertIdx);

    @Insert("INSERT INTO lockAdvert(title, point, link, image) VALUES(#{lockAdvert.title}, #{lockAdvert.point}, #{lockAdvert.link}, #{lockAdvert.image})")
    @Options(useGeneratedKeys = true, keyColumn = "lockAdvertIdx")
    int save(@Param("lockAdvert") final LockAdvert lockAdvert);

    @Update("UPDATE lockAdvert SET title = #{lockAdvert.title}, point = #{lockAdvert.point}, link = #{lockAdvert.link}, image = #{lockAdvert.image} WHERE lockAdvertIdx = #{lockAdvertIdx}")
    void update(@Param("lockAdvertIdx") final int lockAdvertIdx, @Param("lockAdvert") final LockAdvert lockAdvert);

    @Delete("DELETE FROM lockAdvert WHERE lockAdvertIdx = #{lockAdvertIdx}")
    void delete(@Param("lockAdvertIdx") final int lockAdvertIdx);
}
