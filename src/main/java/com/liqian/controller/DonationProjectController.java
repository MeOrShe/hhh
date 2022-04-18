package com.liqian.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.liqian.common.Result;
import com.liqian.common.StringConsts;
import com.liqian.entity.DonationProject;
import com.liqian.service.DonationProjectService;
import com.liqian.utils.DateUtils;
import com.liqian.vo.request.RequestDeleteVo;
import com.liqian.vo.request.RequestProjectListVo;
import com.liqian.vo.request.RequestSaveOrUpdateProjectVo;
import com.liqian.vo.response.ResponseProjectListVo;
import com.liqian.vo.response.ResponseProjectNameVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
* 受捐项目控制层
* */
@RestController
@RequestMapping("/donation-project/")
public class DonationProjectController {

    @Autowired
    private DonationProjectService donationProjectService;

    /*
    * 受捐项目列表查询
    * */
    @PostMapping("list")
    public Result list(@RequestBody RequestProjectListVo requestProjectListVo){
        IPage<ResponseProjectListVo> page = donationProjectService.projectList(requestProjectListVo);
        return Result.getSuccess().setData(page);
    }

    /*
    * 根据companyId查询受捐项目信息
    * */
    @GetMapping("queryByCompanyId/{companyId}")
    public Result queryByCompanyId(@PathVariable Integer companyId){
        List<ResponseProjectNameVo> voList = donationProjectService.queryByCompanyId(companyId);
        return Result.getSuccess().setData(voList);
    }

    /*
     * 通过id查询受捐项目
     * */
    @GetMapping("getById/{id}")
    public Result getById(@PathVariable Integer id){
        DonationProject donationProject = donationProjectService.getById(id);
        return Result.getSuccess().setData(donationProject);
    }

    @DeleteMapping("deleteByIds")
    public Result deleteByIds(@RequestBody RequestDeleteVo deleteVo){
        List<Integer> ids = Arrays.asList(deleteVo.getIds());
        if(ids.size() > 0 && donationProjectService.removeByIds(ids)){
            return Result.getSuccess().setMsg(StringConsts.DELETE_SUCCESS);
        }else {
            return Result.getFailure().setMsg(StringConsts.DELETE_FAIL);
        }
    }

    /*
    * 保存或修改受捐项目
    * */
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody RequestSaveOrUpdateProjectVo saveOrUpdateProjectVo){
        DonationProject donationProject = new DonationProject();
        BeanUtils.copyProperties(saveOrUpdateProjectVo,donationProject);
        donationProject.setStartTime(DateUtils.getDateTime(saveOrUpdateProjectVo.getStartTime()));
        donationProject.setEndTime(DateUtils.getDateTime(saveOrUpdateProjectVo.getEndTime()));
        donationProject.setProjectStatus(0);
        if(donationProjectService.saveOrUpdate(donationProject)){
            return Result.getSuccess().setMsg(StringConsts.SAVE_OR_UPDATE_SUCCESS);
        }else {
            return Result.getFailure().setMsg(StringConsts.SAVE_OR_UPDATE_FAIL);
        }
    }
}
