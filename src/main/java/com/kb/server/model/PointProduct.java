package com.kb.server.model;

import lombok.Data;

/**
 * Created by ehay@naver.com on 2018-11-12
 * Blog : http://ehay.tistory.com
 * Github : http://github.com/ehayand
 */

@Data
public class PointProduct {
    private int pointProductIdx;
    private int pointProductCategoryIdx;
    private long price;
    private String name;
    private String image;
}
