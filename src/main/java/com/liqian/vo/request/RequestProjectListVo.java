package com.liqian.vo.request;

import com.liqian.common.Search;
import lombok.Data;

/*
* 受捐项目列表请求Vo
* */
@Data
public class RequestProjectListVo extends Search {
    private String projectName;
    private Integer projectStatus;
}
