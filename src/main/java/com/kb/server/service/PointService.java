package com.kb.server.service;

import com.kb.server.mapper.PointMapper;
import com.kb.server.model.Point;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class PointService {

    private final PointMapper pointMapper;

    public PointService(PointMapper pointMapper) {
        this.pointMapper = pointMapper;
    }

    public List<Point> findAll() {
        return pointMapper.findAll();
    }

    public Point findByPointIdx(final int pointIdx) {
        return pointMapper.findByPointIdx(pointIdx);
    }

    public Point findByUserIdx(final int userIdx) {
        return pointMapper.findByUserIdx(userIdx);
    }

    public int save(final Point point) {
        return pointMapper.save(point);
    }

    public void update(final int pointIdx, final Point point) {
        pointMapper.update(pointIdx, point);
    }

    public void delete(final int pointIdx) {
        pointMapper.delete(pointIdx);
    }
}
