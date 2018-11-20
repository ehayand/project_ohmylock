package com.kb.server.service;

import com.kb.server.dto.UserDto;
import com.kb.server.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public List<UserDto> findAll() {
        return userMapper.findAll();
    }

    public UserDto findById(final String id) {
        return userMapper.findById(id);
    }

    public UserDto findByUserIdx(final int userIdx) {
        return userMapper.findByUserIdx(userIdx);
    }

    public int save(final UserDto userDto) {
        return userMapper.save(userDto);
    }

    public void update(final int userIdx, final UserDto userDto) {
        userMapper.update(userIdx, userDto);
    }

    public void delete(final int userIdx) {
        userMapper.delete(userIdx);
    }
}
