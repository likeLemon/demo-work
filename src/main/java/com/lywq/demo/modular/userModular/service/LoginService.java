package com.lywq.demo.modular.userModular.service;

import com.lywq.demo.modular.userModular.model.UserInfo;

/**
 * @author 王恩典
 * @title: LoginService
 * @projectName demo
 * @description: TODO
 * @date 2019/12/30 11:23
 */
public interface LoginService {

    // 根据用户名、密码查询用户
    UserInfo queryUserByUserName(String userName,String password);

    // 注册
    String register(UserInfo userInfo);

    // 登录
    Integer login(UserInfo userInfo);
}
