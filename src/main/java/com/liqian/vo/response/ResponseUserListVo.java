package com.liqian.vo.response;

import lombok.Data;

import java.time.LocalDateTime;

/*
* 用户列表查询响应Vo
* */
@Data
public class ResponseUserListVo {
    private Integer id;
    private String username;
    private String telephone;
    private String sex;
    private Integer age;
    private String province;
    private String city;
    private String local;
    private String email;
    private LocalDateTime createTime;
}
