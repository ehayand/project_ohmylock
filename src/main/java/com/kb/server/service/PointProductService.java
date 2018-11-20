package com.kb.server.service;

import com.kb.server.mapper.PointProductMapper;
import com.kb.server.model.PointProduct;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-12
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class PointProductService {

    private final PointProductMapper pointProductMapper;

    public PointProductService(PointProductMapper pointProductMapper) {
        this.pointProductMapper = pointProductMapper;
    }

    public List<PointProduct> findAll(final int page, final int size) {
        return pointProductMapper.findAll(page, size);
    }

    public List<PointProduct> findProductsByCategoryAndName(final int categoryIdx,
                                                            final String name,
                                                            final int page,
                                                            final int size) {

        String likeName = "%" + name + "%";

        return pointProductMapper.findProductsByCategoryAndName(categoryIdx, likeName, page, size);
    }

    public List<PointProduct> findProductsByCategory(final int categoryIdx,
                                                     final int page,
                                                     final int size) {
        return pointProductMapper.findProductsByCategory(categoryIdx, page, size);
    }

    public List<PointProduct> findProductsByName(final String name,
                                                 final int page,
                                                 final int size) {
        String likeName = "%" + name + "%";

        return pointProductMapper.findProductsByName(likeName, page, size);
    }


    public int countAll() {
        return pointProductMapper.countAll();
    }

    public int countProductsByCategoryAndName(final int categoryIdx,
                                              final String name) {
        String likeName = "%" + name + "%";

        return pointProductMapper.countProductsByCategoryAndName(categoryIdx, likeName);
    }

    public int countProductsByCategory(final int categoryIdx) {
        return pointProductMapper.countProductsByCategory(categoryIdx);
    }

    public int countProductsByName(final String name) {
        String likeName = "%" + name + "%";

        return pointProductMapper.countProductsByName(likeName);
    }

}
