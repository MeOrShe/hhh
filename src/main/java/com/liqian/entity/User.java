package com.liqian.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/*
* 用户实体类
* */
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String telephone;
    private Integer type;
    private String sex;
    private Integer age;
    private String province;
    private String city;
    private String local;
    private String email;
    private LocalDateTime createTime;
}
