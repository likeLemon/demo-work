package com.lywq.demo.modular.mailModular.controller;

import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.ApplicationUtil;
import com.lywq.demo.common.utils.MailUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author lywq WED
 * @title: MailController
 * @projectName demo
 * @description: 邮箱控制层
 * @date 2019/11/8 16:19
 */
@RestController
@RequestMapping("/mail")
@Api(value = "邮箱发送", tags = {"邮箱操作接口"}, description = "MailController")
public class MailController {

    /**
     * 发送注册验证码
     *
     * @return 验证码
     * @throws Exception
     */
    @GetMapping("/sendMail")
    public RetResult<String> sendMail(@RequestParam(value = "subject",required = false) String subject,
                                      @RequestParam(value = "toUsers",required = false) String[] toUsers,
                                      @RequestParam(value = "ccUsers",required = false) String[] ccUsers,
                                      @RequestParam(value = "content",required = false) String content,
                                      @RequestParam(value = "attachfiles",required = false) List<Map<String, String>> attachfiles) throws Exception {

        return RetResponse.makeOKRsp();
    }
}