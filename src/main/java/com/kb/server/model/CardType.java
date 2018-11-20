package com.kb.server.model;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class CardType {
    private int cardTypeIdx;
    private int bankIdx;
    private boolean credit; // true = credit, false = check;
    private String name;
    private String image;
}
