package com.liqian.vo.response;

import lombok.Data;

import java.time.LocalDateTime;

/*
* 受捐项目列表响应Vo
* */
@Data
public class ResponseProjectListVo {
    private Integer id;
    private String companyName;
    private String projectLeader;
    private String projectName;
    private String projectDesc;
    private Integer projectStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
}
