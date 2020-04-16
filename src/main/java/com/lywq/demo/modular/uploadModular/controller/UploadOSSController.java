package com.lywq.demo.modular.uploadModular.controller;

import com.aliyun.oss.OSSClient;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.utils.OSSClientUtil;
import com.lywq.demo.common.utils.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @author lywq WED
 * @title: UploadOSSController
 * @projectName demo
 * @description: 上传文件到Oss
 * @date 2019/12/4 9:51
 */
@Api(value = "上传文件到Oss", tags = {"Oss操作接口"}, description = "UploadOSSController")
@RequestMapping("/uploadOss")
@RestController
public class UploadOSSController {

    @PostMapping(value = "/uploadFile")
    @ApiOperation(value = "文件上传", tags = {"Oss操作接口"}, notes = "文件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "文件流对象", required = true, dataType = "__File", allowMultiple = true)
    })
    public RetResult<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 上传文件
        String uploadImgOSS = OSSClientUtil.uploadFileToOSS(ossClient, file);
        // url地址
        String privateUrl = OSSClientUtil.getPrivateUrl(ossClient, uploadImgOSS);
        return RetResponse.makeOKRsp(privateUrl);
    }

    @PostMapping(value = "/mkdir")
    @ApiOperation(value = "创建文件夹", tags = {"Oss操作接口"}, notes = "创建文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "bucketName", value = "存储空间名称", required = true),
            @ApiImplicitParam(name = "folder", value = "文件夹名称", required = true)
    })
    public RetResult<String> mkdir(String bucketName, String folder) {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 创建文件夹
        String fileDir = OSSClientUtil.createFolder(ossClient, bucketName, folder);
        return RetResponse.makeOKRsp(fileDir);
    }

    @GetMapping(value = "deleteFile")
    @ApiOperation(value = "删除文件", tags = {"Oss操作接口"}, notes = "删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名称", required = true)
    })
    public RetResult<String> deleteFile(String fileName) {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 删除文件
        OSSClientUtil.delete(ossClient, fileName);
        return RetResponse.makeOKRsp("删除成功");
    }

//    @ApiOperation(value = "下载文件", notes = "下载文件")
//    @GetMapping(value = "downLoadFile")
//    public RetResult<String> downLoadFile(String fileName){
//        // 获取实例
//        OSSClient ossClient = OSSClientUtil.getOSSClient();
//        OSSObject object = OSSClientUtil.downLoadFile(ossClient,fileName);
//        System.out.println(object);
//        return RetResponse.makeOKRsp("下载成功");
//    }

    @GetMapping("download")
    @ApiOperation(value = "下载文件", tags = {"Oss操作接口"}, notes = "下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileName", value = "文件名称", required = true)
    })
    public RetResult<String> download(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
        // 获取实例
        OSSClient ossClient = OSSClientUtil.getOSSClient();
        // 截取下载名
        String attachmentName = StringUtil.subLastTag(fileName, "/");
        // 通知浏览器以附件形式下载
        response.setHeader("Content-Disposition",
                "attachment;filename=" + new String(attachmentName.getBytes(), "ISO-8859-1"));
        // 下载文件
        OSSClientUtil.downloadFileForOss(ossClient, response.getOutputStream(), fileName);
        return RetResponse.makeOKRsp("下载成功");
    }

}
