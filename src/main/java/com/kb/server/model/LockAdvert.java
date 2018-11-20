package com.kb.server.model;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-12
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class LockAdvert {
    private int lockAdvertIdx;
    private String title;
    private long point;
    private String link;
    private String image;
}
