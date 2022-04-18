package com.liqian.vo.request;

import com.liqian.common.Search;
import lombok.Data;

/*
* 受捐单位列表查询请求Vo
* */
@Data
public class RequestCompanyListVo extends Search {
    private String companyName;
    private String companyAddress;
    private String companyLinkman;
}
