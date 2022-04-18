package com.liqian.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.liqian.common.Result;
import com.liqian.common.StringConsts;
import com.liqian.entity.User;
import com.liqian.service.UserService;
import com.liqian.utils.FormatUtils;
import com.liqian.utils.Md5Utils;
import com.liqian.vo.request.RequestUserListVo;
import com.liqian.vo.request.RequestUserRegisterVo;
import com.liqian.vo.response.RequestLoginVo;
import com.liqian.vo.response.ResponseUserListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/*
* user控制层
* */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Producer producer;

        /*
         * 注册
         * */
        @PostMapping("register")
        public Result register(@RequestBody RequestUserRegisterVo requestUserRegisterVo){
            //判断电话号码格式
            if(!FormatUtils.isMobile(requestUserRegisterVo.getTelephone())){
                return  Result.getFailure().setMsg(StringConsts.PHONE_ERROR);
            }
            //判断电话号码是否已注册
            User user = userService.getByTelephone(requestUserRegisterVo.getTelephone());
            if(StringUtils.checkValNotNull(user)){
                return Result.getFailure().setMsg(StringConsts.USER_EXIST);
            }
            if(userService.register(requestUserRegisterVo)){
                return Result.getSuccess().setMsg(StringConsts.REGISTER_SUCCESS);
            }else {
                return Result.getFailure().setMsg(StringConsts.REGISTER_FAIL);
            }
    }


    /*
    * 验证码
    * */
    @GetMapping("captcha.jpg")
    //Response响应
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        //返回头，不存缓存
        response.setHeader("Cache-Control","no-store,no-cache");
        //返回类型：图片
        response.setContentType("image/jpeg");
        //验证码内容
        String text = producer.createText();
        //生成验证码图片
        BufferedImage image = producer.createImage(text);
        //获取session
        HttpSession session = request.getSession();
        //存入Session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY,text);
        //设置过期时间60s
        session.setMaxInactiveInterval(60);
        //把图片以流的方式传到前端
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"jpg",out);
        //关掉流
        IOUtils.closeQuietly(out);
    }


    /*
    * 登录
    * */
    @PostMapping("login")
    public Result login(HttpServletRequest request,@RequestBody RequestLoginVo requestLoginVo){
        //从session中获取正确的验证码
        HttpSession session = request.getSession();
        String trueCaptcha = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        //如果验证码错误（不区分大小写）
        if ( !trueCaptcha.equalsIgnoreCase(requestLoginVo.getCaptcha())){
            return Result.getFailure().setMsg(StringConsts.CAPTCHA_ERROR);
        }
        //查询对应用户的信息
        User user = userService.queryByTelephone(requestLoginVo.getTelephone());
        //用户不存在或用户密码错误
        if ( user == null || !user.getTelephone().equals(Md5Utils.hash(requestLoginVo.getTelephone()))){
            return Result.getFailure().setMsg(StringConsts.USERNAME_OR_PASSWORD_ERROR);
        }
        //验证码，电话，密码都正确
        //给前端返回需要的信息
        Map<String,Object> userMap = new HashMap<>();
        //某某登录，返回id
        userMap.put("userId",user.getId());
        if(user.getType() == 1){
            userMap.put("isAdmin",true);
            return Result.getSuccess().setData(userMap);
        }else {
            userMap.put("isAdmin",false);
            return Result.getSuccess().setData(userMap);
        }
    }

    /*
    * 用户列表查询
    * */
    @PostMapping("list")
    public Result list(@RequestBody RequestUserListVo requestUserListVo){
        IPage<ResponseUserListVo> page = userService.getUserList(requestUserListVo);
        return Result.getSuccess().setData(page);
    }

    /*
    * 删除用户
    * */
    @DeleteMapping("delete/{userId}")
    public Result delete(@PathVariable("userId") Integer userId){
        //判断是否为管理员
        User user = userService.getById(userId);
        if(StringUtils.checkValNotNull(user)){
            if(user.getType() == 1){
                return Result.getFailure().setMsg(StringConsts.USER_NOT_EXIST);
            }
            if(userService.removeById(userId)){
                return Result.getSuccess().setMsg(StringConsts.DELETE_SUCCESS);
            }else {
                return Result.getFailure().setMsg(StringConsts.DELETE_FAIL);
            }
        }else {
            return Result.getFailure().setMsg(StringConsts.USER_IS_ADMIN);
        }
    }

    /*
    * 通过id查询用户信息
    * */
    @GetMapping("getUserInfoById/{userId}")
    public Result getUserInfoById(@PathVariable("userId") Integer userId){
        User user = userService.getById(userId);
        if (StringUtils.checkValNotNull(user)){
            ResponseUserListVo responseUserListVo = new ResponseUserListVo();
            BeanUtils.copyProperties(user,responseUserListVo);
            return Result.getSuccess().setData(responseUserListVo);
        }else {
            return Result.getFailure().setMsg(StringConsts.USER_NOT_EXIST);
        }
    }
}
