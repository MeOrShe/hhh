package com.liqian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liqian.entity.User;
import com.liqian.mapper.UserMapper;
import com.liqian.service.UserService;
import com.liqian.utils.Md5Utils;
import com.liqian.vo.request.RequestUserListVo;
import com.liqian.vo.request.RequestUserRegisterVo;
import com.liqian.vo.response.ResponseUserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/*
*user服务层
* */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /*
    * 通过电话号码查询用户
    * */
    @Override
    public User getByTelephone(String telephone) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("telephone",telephone);
        return getOne(queryWrapper);
    }

    /*
    * 用户注册
    * */
    @Override
    public boolean register(RequestUserRegisterVo requestUserRegisterVo) {
        User user = new User();
        //拷贝属性
        BeanUtils.copyProperties(requestUserRegisterVo,user);
        user.setTelephone(Md5Utils.hash(requestUserRegisterVo.getTelephone()));
        return save(user);
    }

    /*
     *通过电话号码查询
     * */
    @Override
    public User queryByTelephone(String telephone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("telephone",telephone);
        return getOne(queryWrapper);
    }

    /*
     * 用户列表查询
     * */
    @Override
    public IPage<ResponseUserListVo> getUserList(RequestUserListVo requestUserListVo) {
        //封装分页对象
        IPage<User> page = new Page<>(requestUserListVo.getDisplayStart(),requestUserListVo.getDisplayLength());
        //构造查询器
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.checkValNotNull(requestUserListVo.getEmail())){
            queryWrapper.like("email",requestUserListVo.getEmail());
        }
        if(StringUtils.checkValNotNull(requestUserListVo.getTelephone())){
            queryWrapper.like("telephone",requestUserListVo.getTelephone());
        }
        if(StringUtils.checkValNotNull(requestUserListVo.getUsername())){
            queryWrapper.like("username",requestUserListVo.getUsername());
        }
        //执行分页查询
        page = page(page,queryWrapper);
        //进行属性拷贝
        return page.convert(user -> {
           ResponseUserListVo responseUserListVo = new ResponseUserListVo();
           BeanUtils.copyProperties(user,responseUserListVo);
           return responseUserListVo;
        });
    }


}
