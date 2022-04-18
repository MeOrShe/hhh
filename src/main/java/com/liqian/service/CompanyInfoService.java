package com.liqian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liqian.entity.CompanyInfo;
import com.liqian.vo.request.RequestCompanyListVo;
import com.liqian.vo.response.ResponseCompanyNameVo;

import java.util.List;

/*
* 受捐单位管理
* */
public interface CompanyInfoService extends IService<CompanyInfo> {
    /*
    * 受捐单位列表查询
    * */
    IPage<CompanyInfo> companyList(RequestCompanyListVo requestCompanyListVo);

    /*
    * 获取受捐单位名称及id
    * */
    List<ResponseCompanyNameVo> getAllCompanyName();
}
