package com.liqian.schedule;

/*
*
* */

import com.liqian.entity.DonationProject;
import com.liqian.service.DonationProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

//设置组件
@Component
//开启定时任务
@EnableScheduling
//开启异步
@EnableAsync
public class ProjectScheduleTask {

    @Autowired
    private DonationProjectService projectService;

    @Async
    @Scheduled(fixedDelay = 30000)
    public void updateProjectStatus(){
        //查询所有的受捐项目
        //TODO 查询出受捐项目状态为0的
        List<DonationProject> projectList = projectService.list();
        //获取系统当前时间
        LocalDateTime localDateTime = LocalDateTime.now();
        for (DonationProject donationProject : projectList) {
            if (donationProject.getEndTime().isBefore(localDateTime)){
                donationProject.setProjectStatus(1);
            }
        }
        System.out.println("定时任务正在执行");
        projectService.saveOrUpdateBatch(projectList);
    }

}
