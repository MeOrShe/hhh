package com.liqian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liqian.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* user持久层
* */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
