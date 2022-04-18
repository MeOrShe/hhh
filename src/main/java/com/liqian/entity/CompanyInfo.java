package com.liqian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompanyInfo {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String companyName;
    private String companyAddress;
    private String companyLinkman;
    private String companyPhone;
    private LocalDateTime createTime;
}
