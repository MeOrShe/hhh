package com.liqian.vo.response;

import lombok.Getter;
import lombok.Setter;

/*
* 登录请求Vo
* */
@Setter
@Getter
public class RequestLoginVo {
    private String telephone;
    private String password;
    private String captcha;
}
