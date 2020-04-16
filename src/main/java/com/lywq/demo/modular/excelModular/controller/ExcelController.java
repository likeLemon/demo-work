package com.lywq.demo.modular.excelModular.controller;

import com.lywq.demo.common.constant.ExcelConstant;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.ExcelUtil;
import com.lywq.demo.modular.excelModular.model.ExcelData;
import com.lywq.demo.modular.userModular.model.UserInfo;
import com.lywq.demo.modular.userModular.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 王恩典
 * @title: ExcelController
 * @projectName demo
 * @description: 导出excel表
 * @date 2019/11/8 19:48
 */
@RestController
@RequestMapping("excel")
@Api(value = "导出excel表", tags = {"excel操作接口"}, description = "ExcelController")
public class ExcelController {

    @Resource
    private UserInfoService userInfoService;

    @PostMapping("/exportExcel")
    @ApiOperation(value = "导出excel", tags = {"excel操作接口"}, notes = "导出excel")
    public RetResult<Integer> exportExcel() {
        int rowIndex = 0;
        List<UserInfo> list = userInfoService.selectAll();
        ExcelData data = new ExcelData();
        data.setName("表格名称");
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("userName");
        titles.add("password");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for (int i = 0, length = list.size(); i < length; i++) {
            UserInfo userInfo = list.get(i);
            List<Object> row = new ArrayList();
            row.add(userInfo.getId());
            row.add(userInfo.getUserName());
            row.add(userInfo.getPassword());
            rows.add(row);
        }
        data.setRows(rows);
        try {
            rowIndex = ExcelUtil.generateExcel(data, ExcelConstant.FILE_PATH + ExcelConstant.FILE_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RetResponse.makeOKRsp(Integer.valueOf(rowIndex));
    }

    @GetMapping("/exportExcel2")
    @ApiOperation(value = "exportExcel2", tags = {"excel操作接口"}, notes = "exportExcel2")
    public void exportExcel2(HttpServletResponse response) {
        int rowIndex = 0;
        List<UserInfo> list = userInfoService.selectAll();
        ExcelData data = new ExcelData();
        data.setName("hello");
        List<String> titles = new ArrayList();
        titles.add("ID");
        titles.add("userName");
        titles.add("password");
        data.setTitles(titles);

        List<List<Object>> rows = new ArrayList();
        for (int i = 0, length = list.size(); i < length; i++) {
            UserInfo userInfo = list.get(i);
            List<Object> row = new ArrayList();
            row.add(userInfo.getId());
            row.add(userInfo.getUserName());
            row.add(userInfo.getPassword());
            rows.add(row);
        }
        data.setRows(rows);
        try {
            ExcelUtil.exportExcel(response, "test2", data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
