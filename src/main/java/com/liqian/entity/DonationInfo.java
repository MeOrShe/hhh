package com.liqian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/*
* 捐赠信息
* */
@Data
public class DonationInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer userId;
    private Integer projectId;
    private String projectName;
    private String projectDesc;
    private Integer type;
    private String donor;
    private Integer status;
    private LocalDateTime createTime;
}
