package com.liqian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liqian.entity.DonationProject;
import com.liqian.mapper.DonationProjectMapper;
import com.liqian.service.CompanyInfoService;
import com.liqian.service.DonationProjectService;
import com.liqian.vo.request.RequestProjectListVo;
import com.liqian.vo.response.ResponseCompanyNameVo;
import com.liqian.vo.response.ResponseProjectListVo;
import com.liqian.vo.response.ResponseProjectNameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 受捐项目service实现
* */
@Service
public class DonationProjectServiceImpl extends ServiceImpl<DonationProjectMapper, DonationProject> implements DonationProjectService {

    @Autowired
    private CompanyInfoService companyInfoService;

    /*
     * 分页查询受捐项目列表
     * */
    @Override
    public IPage<ResponseProjectListVo> projectList(RequestProjectListVo requestProjectListVo) {
        //构造分页对象
        IPage<DonationProject> page = new Page<>(requestProjectListVo.getDisplayStart(),requestProjectListVo.getDisplayLength());
        //构建查询器
        QueryWrapper<DonationProject> queryWrapper = new QueryWrapper<>();
        if(StringUtils.checkValNotNull(requestProjectListVo.getProjectName())){
            queryWrapper.like("project_name",requestProjectListVo.getProjectName());
        }
        if (StringUtils.checkValNotNull(requestProjectListVo.getProjectStatus())){
            queryWrapper.eq("project_status",requestProjectListVo.getProjectStatus());
        }
        page = page(page,queryWrapper);
        //获取所有的受捐单位信息
        List<ResponseCompanyNameVo> responseCompanyNameVoList = companyInfoService.getAllCompanyName();
        //将id作为键，name作为值，存在map里
        Map<Integer,String> nameMap = new HashMap<>();
        for (ResponseCompanyNameVo name:responseCompanyNameVoList) {
            nameMap.put(name.getId(),name.getCompanyName());
        }
        //遍历page
        return page.convert(result->{
            ResponseProjectListVo responseProjectListVo = new ResponseProjectListVo();
            BeanUtils.copyProperties(result,responseProjectListVo);
            responseProjectListVo.setCompanyName(nameMap.get(result.getCompanyId()));
            return responseProjectListVo;
        });
    }

    /*
     * 根据companyId查询受捐项目信息
     * */
    @Override
    public List<ResponseProjectNameVo> queryByCompanyId(Integer companyId) {
        QueryWrapper<DonationProject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("company_id",companyId);
        queryWrapper.eq("project_status",0);
        //从数据库中查询出来的数据
        List<DonationProject> donationProjectLists = list(queryWrapper);
        //Vo集合
        List<ResponseProjectNameVo> responseProjectNameVoList = new ArrayList<>();
        for (DonationProject donationProject : donationProjectLists) {
            ResponseProjectNameVo vo = new ResponseProjectNameVo();
            BeanUtils.copyProperties(donationProject,vo);
            responseProjectNameVoList.add(vo);
        }
        return responseProjectNameVoList;
    }
}
