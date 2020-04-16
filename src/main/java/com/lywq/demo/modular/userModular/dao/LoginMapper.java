package com.lywq.demo.modular.userModular.dao;

import com.lywq.demo.modular.userModular.model.UserInfo;
import org.mapstruct.Mapper;

/**
 * @author 王恩典
 * @title: LoginMapper
 * @projectName demo
 * @description: TODO
 * @date 2019/12/30 11:22
 */
@Mapper
public interface LoginMapper {

    // 根据用户名、密码查询用户
    UserInfo queryUserByUserName(String userName,String password);

    // 注册
    Integer register(UserInfo userInfo);
}
