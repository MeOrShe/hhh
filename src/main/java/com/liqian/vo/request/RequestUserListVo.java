package com.liqian.vo.request;

import com.liqian.common.Search;
import lombok.Data;

/*
* 用户管理列表查询请求Vo
* */
@Data
public class RequestUserListVo extends Search {
    private String email;
    private String telephone;
    private String username;
}
