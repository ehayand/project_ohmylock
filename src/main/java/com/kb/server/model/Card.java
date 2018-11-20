package com.kb.server.model;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class Card {
    private int cardIdx;
    private int userIdx;
    private int accountIdx;
    private int cardTypeIdx;
    private long number;
    private long cardLimit;
    private boolean view;
}
