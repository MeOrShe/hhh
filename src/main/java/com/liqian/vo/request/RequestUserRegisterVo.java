package com.liqian.vo.request;

import lombok.Data;
/*
* 用户注册请求VO
* */
@Data
public class RequestUserRegisterVo {
    private String username;
    private String password;
    private String telephone;
    private String sex;
    private Integer age;
    private String province;
    private String city;
    private String local;
    private String email;
}
