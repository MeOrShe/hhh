package com.liqian.vo.request;

import lombok.Data;

/*
* 保存或修改受捐项目请求Vo
* */
@Data
public class RequestSaveOrUpdateProjectVo {
    private Integer id;
    private Integer companyId;
    private String projectLeader;
    private String projectName;
    private String projectDesc;
    private Integer projectStatus;
    private Long startTime;
    private Long endTime;
}
