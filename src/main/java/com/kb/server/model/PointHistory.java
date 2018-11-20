package com.kb.server.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class PointHistory {
    private int pointHistoryIdx;
    private int pointIdx;
    private boolean earn; // true = 적립, false = 사용
    private long difference;
    private LocalDateTime time;
}
