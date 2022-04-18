package com.liqian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liqian.entity.CompanyInfo;
import com.liqian.mapper.CompanyInfoMapper;
import com.liqian.service.CompanyInfoService;
import com.liqian.vo.request.RequestCompanyListVo;
import com.liqian.vo.response.ResponseCompanyNameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
* 受捐实现类
* */
@Service
public class CompanyInfoServiceImpl extends ServiceImpl<CompanyInfoMapper, CompanyInfo> implements CompanyInfoService{


    /*
    * 受捐单位列表查询
    * */
    @Override
    public IPage<CompanyInfo> companyList(RequestCompanyListVo requestCompanyListVo) {
        //构造分页对象
        IPage<CompanyInfo> page = new Page<>(requestCompanyListVo.getDisplayStart(),requestCompanyListVo.getDisplayLength());
        //创建查询工具
        QueryWrapper<CompanyInfo> queryWrapper = new QueryWrapper<>();
        //公司地址模糊查询
        if(StringUtils.checkValNotNull(requestCompanyListVo.getCompanyAddress())){
            queryWrapper.like("company_address",requestCompanyListVo.getCompanyAddress());
        }
        //公司名称模糊查询
        if(StringUtils.checkValNotNull(requestCompanyListVo.getCompanyName())){
            queryWrapper.like("company_name",requestCompanyListVo.getCompanyName());
        }
        //公司联系人模糊查询
        if(StringUtils.checkValNotNull(requestCompanyListVo.getCompanyLinkman())){
            queryWrapper.like("company_linkman",requestCompanyListVo.getCompanyLinkman());
        }
        return page(page,queryWrapper);
    }

    /*
    * 获取受捐单位名称及id
    * */
    @Override
    public List<ResponseCompanyNameVo> getAllCompanyName() {
        //查询数据库中所有的受捐单位信息
        List<CompanyInfo> companyInfoList =list();
        //定义需要返回的数据集合
        List<ResponseCompanyNameVo> responseCompanyNameVoList = new ArrayList<>();
        //复制companyInfoList中的属性去responseCompanyNameVoList中
        for(CompanyInfo companyInfo:companyInfoList){
            ResponseCompanyNameVo companyNameVo = new ResponseCompanyNameVo();
            //拷贝相同属性
            BeanUtils.copyProperties(companyInfo,companyNameVo);
            responseCompanyNameVoList.add(companyNameVo);
        }
        return responseCompanyNameVoList;
    }
}
