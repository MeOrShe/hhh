package com.liqian.vo.response;

import lombok.Data;

/*
* 根据受捐单位id查询受捐项目
* */
@Data
public class ResponseProjectNameVo {
    private Integer id;
    private String projectName;
    private String projectDesc;
}
