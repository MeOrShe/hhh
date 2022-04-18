package com.liqian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemList {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer donationInfoId;
    private String currency;
    private Float amount;
    private String itemName;
    private Integer itemAmount;
    private String supplier;
    private String standard;
    private LocalDateTime createTime;
}
