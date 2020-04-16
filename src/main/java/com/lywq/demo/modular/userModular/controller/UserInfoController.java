package com.lywq.demo.modular.userModular.controller;

import com.github.pagehelper.PageInfo;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.modular.userModular.model.UserInfo;
import com.lywq.demo.modular.userModular.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lywq WED
 * @title: UserInfoController
 * @projectName demo
 * @description: TODO
 * @date 2019/11/5 19:52
 */
@RestController
@RequestMapping("userInfo")
@Api(value = "用户操作Controler", tags = {"用户操作接口"}, description = "userInfoControler")
public class UserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @GetMapping("/hello")
    @ApiOperation(value = "你好世界", tags = {"测试controller"}, notes = "controller启动")
    public String hello() {
        return "hello";
    }

    @GetMapping("/selectById")
    @ApiOperation(value = "查询用户", tags = {"用户操作接口"}, notes = "根据用户ID查询用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true)
    })
    public RetResult<UserInfo> selectById(@RequestParam String id) {
        UserInfo userInfo = userInfoService.selectById(id);
        return RetResponse.makeOKRsp(userInfo);
    }

    @PostMapping("/testException")
    @ApiOperation(value = "测试异常", tags = {"测试controller"}, notes = "测试服务异常")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true)
    })
    public RetResult<UserInfo> testException(@RequestParam String id) {
        List a = null;
        a.size();
        UserInfo userInfo = userInfoService.selectById(id);
        return RetResponse.makeOKRsp(userInfo);
    }

    @PostMapping("/selectAll")
    @ApiOperation(value = "查询用户", tags = {"用户操作接口"}, notes = "分页查询用户所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码"),
            @ApiImplicitParam(name = "size", value = "每页显示条数")
    })
    public RetResult<PageInfo<UserInfo>> selectAll(@RequestParam(defaultValue = "0") Integer page,
                                                   @RequestParam(defaultValue = "0") Integer size) {
        PageInfo<UserInfo> pageInfo = userInfoService.selectAll(page, size);
        return RetResponse.makeOKRsp(pageInfo);
    }

}

