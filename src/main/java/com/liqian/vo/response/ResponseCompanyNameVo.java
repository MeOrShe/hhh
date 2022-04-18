package com.liqian.vo.response;

import lombok.Data;

/*
* 返回受捐单位名称及id
* */
@Data
public class ResponseCompanyNameVo {
    private Integer id;
    private String companyName;
}
