package com.lywq.demo.modular.redisModular.controller;

import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author lywq WED
 * @title: RedisController
 * @projectName demo
 * @description: redis操作接口
 * @date 2019/11/6 9:49
 */
@RestController
@RequestMapping("redis")
@Api(value = "redis操作Controler", tags = {"redis操作接口"}, description = "userInfoControler")
public class RedisController {

    @PostMapping("/setRedis")
    @ApiOperation(value = "设置redis", tags = {"redis操作接口"}, notes = "新增或修改redis")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "key", required = true),
            @ApiImplicitParam(name = "value", value = "value", required = true)
    })
    public RetResult<String> setRedis(String name, String value) {
        RedisUtil.set(name, value);
        return RetResponse.makeOKRsp(name);
    }

    @PostMapping("/getRedis")
    @ApiOperation(value = "获取redis值", tags = {"redis操作接口"}, notes = "通过那么获取value")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", required = true)
    })
    public RetResult<Object> getRedis(String name) {
        Object value = RedisUtil.get(name);
        return RetResponse.makeOKRsp(value);
    }
}