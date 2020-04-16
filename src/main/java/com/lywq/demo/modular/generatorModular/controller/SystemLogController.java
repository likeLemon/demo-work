package com.lywq.demo.modular.generatorModular.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.ApplicationUtil;
import com.lywq.demo.modular.generatorModular.model.SystemLog;
import com.lywq.demo.modular.generatorModular.service.SystemLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lywq WED
 * @Description: SystemLogController类
 * @date 2019/11/30 11:40
 */
@RestController
@RequestMapping("/systemLog")
@Api(value = "SystemLog", tags = {"SystemLog操作接口"}, description = "SystemLog")
public class SystemLogController {

    @Resource
    private SystemLogService systemLogService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增SystemLog", tags = {"SystemLog操作接口"}, notes = "新增SystemLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemLog", value = "systemLog", required = true)
    })
    public RetResult<Integer> insert(SystemLog systemLog) throws Exception {
        systemLog.setId(ApplicationUtil.getUUID());
        Integer state = systemLogService.insert(systemLog);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    @ApiOperation(value = "删除SystemLog", tags = {"SystemLog操作接口"}, notes = "删除SystemLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = systemLogService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改SystemLog", tags = {"SystemLog操作接口"}, notes = "修改SystemLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "systemLog", value = "systemLog", required = true)
    })
    public RetResult<Integer> update(SystemLog systemLog) throws Exception {
        Integer state = systemLogService.update(systemLog);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "查询SystemLog", tags = {"SystemLog操作接口"}, notes = "查询SystemLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public RetResult<SystemLog> selectById(@RequestParam String id) throws Exception {
        SystemLog systemLog = systemLogService.selectById(id);
        return RetResponse.makeOKRsp(systemLog);
    }

    /**
     * @param page 页码
     * @param size 每页条数
     * @Description: 分页查询
     * @Reutrn RetResult<PageInfo < SystemLog>>
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询SystemLog", tags = {"SystemLog操作接口"}, notes = "分页查询SystemLog")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码"),
            @ApiImplicitParam(name = "size", value = "每页显示条数")
    })
    public RetResult<PageInfo<SystemLog>> list(@RequestParam(defaultValue = "1") Integer page,
                                               @RequestParam(defaultValue = "10") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<SystemLog> list = systemLogService.selectAll();
        PageInfo<SystemLog> pageInfo = new PageInfo<SystemLog>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }

    @GetMapping("/selectAll")
    @ApiOperation(value="查询所有日志文件",tags={"SystemLog操作接口"},notes = "查询所有日志文件")
    public List<SystemLog> selectAll(){
        List<SystemLog> systemLog=systemLogService.selectAll();
        return systemLog;
    }


    @GetMapping("/deleteSys")
    @ApiOperation(value="查询所有日志文件",tags={"SystemLog操作接口"},notes = "查询所有日志文件")
    public List<SystemLog> deleteSys(){
        List<SystemLog> systemLog=systemLogService.selectAll();
        return systemLog;
    }
}