package com.kb.server.mapper;

import com.kb.server.model.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-15
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface CategoryMapper {

    @Select("SELECT * FROM category")
    List<Category> findAll();

    @Select("SELECT * FROM category WHERE name = #{name}")
    List<Category> findCategoryByName(@Param("name") final String name);

}
