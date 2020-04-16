package com.lywq.demo.modular.userModular.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.Set;


/**
 * @author lywq WED
 * @title: UserInfo
 * @projectName demo
 * @description: 用户实体类
 * @date 2019/11/5 19:48
 */
@Data
@ApiModel(value = "user对象", description = "用户对象user")
public class UserInfo {
    /**
     * 主键
     */
    @Id
    @ApiModelProperty(value = "用户id", name = "id")
    private String id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "userName", example = "lywq")
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", name = "password", example = "123")
    private String password;

    /**
     * 用户邮箱
     */
    @ApiModelProperty(value = "用户邮箱", name = "email", example = "123@qq.com")
    private String email;

    /**
     * 用户手机号
     */
    @ApiModelProperty(value = "用户手机号", name = "phoneNum", example = "123")
    private String phoneNum;

    /**
     * 加密盐值
     */
    @ApiModelProperty(value = "加密盐值", name = "salt")
    private String salt;

    /**
     * 用户所有角色值，用于shiro做角色权限的判断
     */
    @Transient
    @ApiModelProperty(value = "用户所有角色值，用于shiro做角色权限的判断", name = "roles")
    private String roles;

    /**
     * 用户所有权限值，用于shiro做资源权限的判断
     */
    @Transient
    @ApiModelProperty(value = "用户所有权限值，用于shiro做资源权限的判断", name = "perms")
    private Set<String> perms;

    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "身份证")
    private String idcard;
    @ApiModelProperty(value = "创建时间")
    private String cjdata;
    @ApiModelProperty(value = "微信号")
    private String wechat;
    @ApiModelProperty(value = "联系地址")
    private String address;

}
