package com.liqian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/*
* 受捐项目
* */
@Data
public class DonationProject {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer companyId;
    private String projectLeader;
    private String projectName;
    private String projectDesc;
    private Integer projectStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime createTime;
}
