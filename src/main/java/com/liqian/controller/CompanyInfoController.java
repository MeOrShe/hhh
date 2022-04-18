package com.liqian.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liqian.common.Result;
import com.liqian.common.StringConsts;
import com.liqian.entity.CompanyInfo;
import com.liqian.service.CompanyInfoService;
import com.liqian.vo.request.RequestCompanyListVo;
import com.liqian.vo.request.RequestDeleteVo;
import com.liqian.vo.response.ResponseCompanyNameVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/*
* 授权单位管理控制器
* */
@RestController
@RequestMapping("/company-info/")
public class CompanyInfoController {

    @Autowired
    private CompanyInfoService companyInfoService;

    /*
    *受捐单位列表查询
    * */
    @PostMapping("list")
    public Result list(@RequestBody RequestCompanyListVo requestCompanyListVo){
        IPage<CompanyInfo> page = companyInfoService.companyList(requestCompanyListVo);
        return Result.getSuccess().setData(page);
    }

    /*
    * 根据id查询受捐单位信息
    * */
    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id){
        return Result.getSuccess().setData(companyInfoService.getById(id));
    }

    /*
    *保存或修改受捐单位信息
    * */
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody CompanyInfo companyInfo){
        if(companyInfoService.saveOrUpdate(companyInfo)){
            return Result.getSuccess().setMsg(StringConsts.SAVE_OR_UPDATE_SUCCESS);
        }else {
            return Result.getFailure().setMsg(StringConsts.SAVE_OR_UPDATE_FAIL);
        }
    }

    /*
    * 批量删除
    * */
    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody RequestDeleteVo requestDeleteVo){
        List<Integer> ids = Arrays.asList(requestDeleteVo.getIds());
        if(ids.size() > 0 && companyInfoService.removeByIds(ids)){
            return Result.getSuccess().setMsg(StringConsts.DELETE_SUCCESS);
        }else {
            return Result.getFailure().setMsg(StringConsts.DELETE_FAIL);
        }
    }

    /*
     * 获取受捐单位名称及id
     * */
    @GetMapping("getAllCompanyName")
    public Result getAllCompanyName(){
        List<ResponseCompanyNameVo> companyNameVoList = companyInfoService.getAllCompanyName();
        return Result.getSuccess().setData(companyNameVoList);
    }
}
