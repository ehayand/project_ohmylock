package com.kb.server.mapper;

import com.kb.server.model.PointProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-12
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface PointProductMapper {

    @Select("SELECT * FROM pointProduct ORDER BY pointProductIdx ASC LIMIT #{page},#{size}")
    List<PointProduct> findAll(@Param("page") final int page,
                               @Param("size") final int size);

    @Select("SELECT * FROM pointProduct WHERE categoryIdx = #{categoryIdx} AND name LIKE #{name} ORDER BY pointProductIdx ASC LIMIT #{page},#{size}")
    List<PointProduct> findProductsByCategoryAndName(@Param("categoryIdx") final int categoryIdx,
                                                     @Param("name") final String name,
                                                     @Param("page") final int page,
                                                     @Param("size") final int size);

    @Select("SELECT * FROM pointProduct WHERE categoryIdx = #{categoryIdx} ORDER BY pointProductIdx ASC LIMIT #{page},#{size}")
    List<PointProduct> findProductsByCategory(@Param("categoryIdx") final int categoryIdx,
                                              @Param("page") final int page,
                                              @Param("size") final int size);

    @Select("SELECT * FROM pointProduct WHERE name LIKE #{name} ORDER BY pointProductIdx ASC LIMIT #{page},#{size}")
    List<PointProduct> findProductsByName(@Param("name") final String name,
                                          @Param("page") final int page,
                                          @Param("size") final int size);

    @Select("SELECT COUNT(pointProductIdx) FROM pointProduct")
    int countAll();

    @Select("SELECT COUNT(pointProductIdx) FROM pointProduct WHERE categoryIdx = #{categoryIdx} AND name LIKE #{name}")
    int countProductsByCategoryAndName(@Param("categoryIdx") final int categoryIdx,
                                       @Param("name") final String name);

    @Select("SELECT COUNT(pointProductIdx) FROM pointProduct WHERE categoryIdx = #{categoryIdx}")
    int countProductsByCategory(@Param("categoryIdx") final int categoryIdx);

    @Select("SELECT COUNT(pointProductIdx) FROM pointProduct WHERE name LIKE #{name}")
    int countProductsByName(@Param("name") final String name);

}
