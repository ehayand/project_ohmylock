package com.kb.server.mapper;

import com.kb.server.dto.UserDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<UserDto> findAll();

    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDto findById(@Param("id") final String id);

    @Select("SELECT * FROM user WHERE userIdx = #{userIdx}")
    UserDto findByUserIdx(@Param("userIdx") final int userIdx);

    @Insert("INSERT INTO user(name, id, password, email, phone, role) VALUES(#{userDto.name}, #{userDto.id}, #{userDto.password}, #{userDto.email}, #{userDto.phone}, #{userDto.role})")
    @Options(useGeneratedKeys = true, keyColumn = "userDto.userIdx")
    int save(@Param("userDto") final UserDto userDto);

    @Update("UPDATE user SET password = #{userDto.password}, email = #{userDto.email}, phone = #{userDto.phone} WHERE userIdx = #{userIdx}")
    void update(@Param("userIdx") final int userIdx, @Param("userDto") final UserDto userDto);

    @Delete("DELETE FROM user WHERE userIdx = #{userIdx}")
    void delete(@Param("userIdx") final int userIdx);
}
