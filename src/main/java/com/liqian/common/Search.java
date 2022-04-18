package com.liqian.common;

import lombok.Data;

/*
* 搜索父类
* */
@Data
public class Search {
    //开始查询页数
    private Long displayStart;
    //每页展示条数
    private Long displayLength;
}
