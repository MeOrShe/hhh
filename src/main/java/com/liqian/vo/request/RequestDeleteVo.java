package com.liqian.vo.request;

import lombok.Data;

/*
* 批量删除请求Vo
* */
@Data
public class RequestDeleteVo {
    Integer[] ids;
}
