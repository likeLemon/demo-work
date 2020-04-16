package com.lywq.demo.modular.generatorModular.controller;

import com.lywq.demo.common.utils.EncryptUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王恩典
 * @title: TestController
 * @projectName demo
 * @description: 测试controller
 * @date 2019/12/12 9:26
 */
@RestController
@RequestMapping("/test")
@Api(value = "测试", tags = {"测试操作接口"}, description = "测试")
public class TestController {
    @GetMapping("/testMD5")
    @ApiOperation(value = "测试md5加密",tags = {"测试操作接口"},notes = "测试md5加密")
    public String testMD5(String res){
        return EncryptUtil.MD5(res);
    }
    @GetMapping("/testMD5Key")
    @ApiOperation(value = "测试md5密钥加密",tags = {"测试操作接口"},notes = "测试md5密钥加密")
    public String testMD5Key(String res, String key){
        return EncryptUtil.MD5(res,key);
    }
    @GetMapping("/testSHA1")
    @ApiOperation(value = "测试SHA1加密",tags = {"测试操作接口"},notes = "测试SHA1加密")
    public String testSHA1(String res){
        return EncryptUtil.SHA1(res);
    }
    @GetMapping("/testSHA1Key")
    @ApiOperation(value = "测试SHA1密钥加密",tags = {"测试操作接口"},notes = "测试SHA1密钥加密")
    public String testSHA1Key(String res, String key){
        return EncryptUtil.SHA1(res,key);
    }
}
