package com.liqian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/*
* 公告实体类
* */
@Data
public class Notice {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String noticeText;
    private LocalDateTime createTime;
}
