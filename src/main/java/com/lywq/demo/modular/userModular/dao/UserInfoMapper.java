package com.lywq.demo.modular.userModular.dao;

import com.lywq.demo.common.base.Mapper;
import com.lywq.demo.modular.userModular.model.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lywq WED
 * @title: UserInfoMapper
 * @projectName demo
 * @description: TODO
 * @date 2019/11/5 19:49
 */
@Repository
public interface UserInfoMapper extends Mapper<UserInfo> {

    UserInfo selectById(@Param("id") String id);

    @Override
    List<UserInfo> selectAll();
}
