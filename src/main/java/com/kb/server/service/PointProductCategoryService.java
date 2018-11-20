package com.kb.server.service;

import com.kb.server.mapper.CategoryMapper;
import com.kb.server.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-15
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class PointProductCategoryService {

    private final CategoryMapper categoryMapper;

    public PointProductCategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    public List<Category> findAll() {
        return categoryMapper.findAll();
    }

    public List<Category> findCategoryByName(final String name) {
        return categoryMapper.findCategoryByName(name);
    }
}
