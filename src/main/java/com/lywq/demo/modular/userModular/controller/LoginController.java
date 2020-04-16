package com.lywq.demo.modular.userModular.controller;

import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.ApplicationUtil;
import com.lywq.demo.common.utils.MailUtil;
import com.lywq.demo.common.utils.StringUtil;
import com.lywq.demo.modular.userModular.model.UserInfo;
import com.lywq.demo.modular.userModular.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 王恩典
 * @title: LoginController
 * @projectName demo
 * @description: 登录
 * @date 2019/12/30 11:19
 */
@RestController
@RequestMapping("login")
@Api(value = "登录controller", tags = {"登录操作接口"}, description = "LoginController")
public class LoginController {

    @Resource
    private LoginService loginService;

    @GetMapping(value = "/emailCheck")
    @ApiOperation(value = "邮箱注册", tags = {"登录controller"}, notes = "邮箱注册")
    public RetResult<String> emailCheck(String userEmail){
        // 生成验证码
        String identifyingCode = StringUtil.getNumStringRandom(6);
        // 邮箱发送验证码
        String[] email = new String[]{userEmail};
        Boolean tag = MailUtil.sendEmail("注册验证码",email,null,identifyingCode,null);
        if (tag){
            return RetResponse.makeOKRsp("验证码已发送，请查收邮件");
        }
        if (!tag){
            return RetResponse.makeErrRsp("验证码发送失败，请重新获取");
        }
        return null;
    }

    @PostMapping(value = "/register")
    @ApiOperation(value = "用户注册", tags = {"登录controller"}, notes = "用户注册")
    public RetResult<String> register(UserInfo userInfo) {
        return RetResponse.makeOKRsp(loginService.register(userInfo));
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "用户登录", tags = {"登录controller"}, notes = "用户登录")
    public RetResult<String> login(UserInfo userInfo) {
        Integer tag = loginService.login(userInfo);
        // 登录成功
        if (tag==0){
            return RetResponse.makeRsp(200,"登录成功");
        }
        // 用户不存在
        if (tag==1){
            return RetResponse.makeRsp(300,"用户不存在");
        }
        // 用户名或密码错误
        if (tag==2){
            return RetResponse.makeRsp(400,"用户名或密码错误");
        }
        return null;
    }
}
