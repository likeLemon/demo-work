package com.lywq.demo.modular.userModular.service.impl;

import com.lywq.demo.common.utils.EncryptUtil;
import com.lywq.demo.modular.userModular.dao.LoginMapper;
import com.lywq.demo.modular.userModular.model.UserInfo;
import com.lywq.demo.modular.userModular.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 王恩典
 * @title: LoginServiceImpl
 * @projectName demo
 * @description: TODO
 * @date 2019/12/30 11:23
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private LoginMapper loginMapper;

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    @Override
    public UserInfo queryUserByUserName(String userName,String password) {
        return loginMapper.queryUserByUserName(userName,password);
    }

    /**
     * 注册
     * @param userInfo
     * @return
     */
    @Override
    public String register(UserInfo userInfo) {

        // 查询用户名是否存在
        UserInfo userInfoHave = loginMapper.queryUserByUserName(userInfo.getUserName(),null);
        if (userInfoHave==null){
            // 用户不存在

            // 生成随机盐
            String salt = EncryptUtil.MD5(userInfo.getUserName());

            // 使用SHA1加密算法进行加密（不可逆）
            String passWord = EncryptUtil.SHA1(userInfo.getPassword(),salt);

            userInfo.setSalt(salt);
            userInfo.setPassword(passWord);

            Integer num = loginMapper.register(userInfo);
            if (num==1){
                return "用户注册成功！";
            }else {
                return "用户注册失败！";
            }
        }
        if (userInfoHave!=null){
            // 用户已存在
            return "用户已存在！";
        }
        return null;
    }

    /**
     * 用户登录
     * @param userInfo
     * @return
     */
    @Override
    public Integer login(UserInfo userInfo) {
        // 查询用户名是否存在
        UserInfo userInfoHave = loginMapper.queryUserByUserName(userInfo.getUserName(),null);
        if (userInfoHave==null){
            // 用户不存在
            return 1;
        }
        if (userInfoHave!=null){
            // 获取用户信息的盐值
            String salt = userInfoHave.getSalt();
            // 获取用户信息的密码
            String passWordTrue = userInfoHave.getPassword();

            // 密码校验
            String passWord = EncryptUtil.SHA1(userInfo.getPassword(),salt);
            if (passWord.equals(passWordTrue)){
                // 密码正确
                return 0;
            }
            if (!passWord.equals(passWordTrue)){
                // 用户名或密码错误
                return 2;
            }
        }
        return null;
    }
}
