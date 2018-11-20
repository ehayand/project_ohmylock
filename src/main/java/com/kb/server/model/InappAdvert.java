package com.kb.server.model;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-11
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class InappAdvert {
    private int inappAdvertIdx;
    private String title;
    private long point;
    private String link;
    private String image;
}
