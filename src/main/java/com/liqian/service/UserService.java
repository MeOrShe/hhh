package com.liqian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liqian.entity.User;
import com.liqian.vo.request.RequestUserListVo;
import com.liqian.vo.request.RequestUserRegisterVo;
import com.liqian.vo.response.ResponseUserListVo;

public interface UserService extends IService<User> {

    /*
    * 通过电话号码查询用户
    * */
    User getByTelephone(String telephone);

    /*
     *用户注册
     * */
    boolean register(RequestUserRegisterVo requestUserRegisterVo);

    /*
     *通过电话号码查询
     * */
    User queryByTelephone(String telephone);

    /*
    * 用户列表查询
    * */
    IPage<ResponseUserListVo> getUserList(RequestUserListVo requestUserListVo);
}
