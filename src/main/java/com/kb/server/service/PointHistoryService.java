package com.kb.server.service;

import com.kb.server.mapper.PointHistoryMapper;
import com.kb.server.model.PointHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Service
public class PointHistoryService {

    private final PointHistoryMapper pointHistoryMapper;

    public PointHistoryService(PointHistoryMapper pointHistoryMapper) {
        this.pointHistoryMapper = pointHistoryMapper;
    }

    public List<PointHistory> findAll() {
        return pointHistoryMapper.findAll();
    }

    public List<PointHistory> findPointHistoriesByPointIdx(final int pointIdx, final int page, final int size) {
        return pointHistoryMapper.findPointHistoriesByPointIdx(pointIdx, page, size);
    }

    public int countPointHistoriesByPointIdx(final int pointIdx) {
        return pointHistoryMapper.countPointHistoriesByPointIdx(pointIdx);
    }

    public List<PointHistory> findPointHistoriesByPointIdxAndIsEarn(final int pointIdx, final boolean isEarn) {
        return pointHistoryMapper.findPointHistoriesByPointIdxAndIsEarn(pointIdx, isEarn);
    }

    public PointHistory findByPointHistoryIdx(final int pointHistoryIdx) {
        return pointHistoryMapper.findByPointHistoryIdx(pointHistoryIdx);
    }

    public int save(final PointHistory pointHistory) {
        return pointHistoryMapper.save(pointHistory);
    }

    public void update(final int pointHistoryIdx, final PointHistory pointHistory) {
        pointHistoryMapper.update(pointHistoryIdx, pointHistory);
    }

    public void delete(final int pointHistoryIdx) {
        pointHistoryMapper.delete(pointHistoryIdx);
    }
}
