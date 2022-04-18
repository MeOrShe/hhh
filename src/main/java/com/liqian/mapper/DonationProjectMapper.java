package com.liqian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liqian.entity.DonationProject;
import org.apache.ibatis.annotations.Mapper;

/*
* 受捐项目持久层
* */
@Mapper
public interface DonationProjectMapper extends BaseMapper<DonationProject> {
}
