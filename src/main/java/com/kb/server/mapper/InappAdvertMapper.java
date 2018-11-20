package com.kb.server.mapper;

import com.kb.server.model.InappAdvert;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface InappAdvertMapper {

    @Select("SELECT * FROM inappAdvert ORDER BY inappAdvertIdx ASC LIMIT #{page},#{size}")
    List<InappAdvert> findAll(@Param("page") final int page,
                              @Param("size") final int size);

    @Select("SELECT COUNT(inappAdvertIdx) FROM inappAdvert")
    int countAll();

    @Select("SELECT * FROM inappAdvert WHERE title = #{title}")
    List<InappAdvert> findInappAdvertsByAdvertiser(@Param("title") final String title);

    @Select("SELECT * FROM inappAdvert WHERE inappAdvertIdx = #{inappAdvertIdx}")
    InappAdvert findByInappAdvertIdx(@Param("inappAdvertIdx") final int inappAdvertIdx);

    @Insert("INSERT INTO inappAdvert(title, point, link, image) VALUES(#{inappAdvert.title}, #{inappAdvert.point}, #{inappAdvert.link}, #{inappAdvert.image})")
    @Options(useGeneratedKeys = true, keyColumn = "inappAdvertIdx")
    int save(@Param("inappAdvert") final InappAdvert inappAdvert);

    @Update("UPDATE inappAdvert SET title = #{inappAdvert.title}, point = #{inappAdvert.point}, link = #{inappAdvert.link}, image = #{inappAdvert.image} WHERE inappAdvertIdx = #{inappAdvertIdx}")
    void update(@Param("inappAdvertIdx") final int inappAdvertIdx, @Param("inappAdvert") final InappAdvert inappAdvert);

    @Delete("DELETE FROM inappAdvert WHERE inappAdvertIdx = #{inappAdvertIdx}")
    void delete(@Param("inappAdvertIdx") final int inappAdvertIdx);
}
