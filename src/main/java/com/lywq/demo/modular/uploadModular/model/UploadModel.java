package com.lywq.demo.modular.uploadModular.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lywq WED
 * @title: UploadModel
 * @projectName demo
 * @description: 文件上传实体类
 * @date 2019/11/29 14:25
 */
@Data
@ApiModel(value = "文件对象", description = "文件对象")
public class UploadModel {

    @ApiModelProperty(dataType = "", value = "文件名")
    private String name;

    @ApiModelProperty(dataType = "", value = "上传文件")
    private MultipartFile file;

}