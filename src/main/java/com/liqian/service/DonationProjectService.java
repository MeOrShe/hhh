package com.liqian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liqian.entity.DonationProject;
import com.liqian.vo.request.RequestProjectListVo;
import com.liqian.vo.response.ResponseProjectListVo;
import com.liqian.vo.response.ResponseProjectNameVo;

import java.util.List;

/*
* 受捐项目service
* */
public interface DonationProjectService extends IService<DonationProject> {

    /*
    * 分页查询受捐项目列表
    * */
    IPage<ResponseProjectListVo> projectList(RequestProjectListVo requestProjectListVo);

    /*
     * 根据companyId查询受捐项目信息
     * */
    List<ResponseProjectNameVo> queryByCompanyId(Integer companyId);
}
