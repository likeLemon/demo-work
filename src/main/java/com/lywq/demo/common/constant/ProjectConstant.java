package com.lywq.demo.common.constant;

/**
 * @description: 系统常用变量
 * @author: lywq WED
 * @time: 2019/11/5 22:56
 */
public class ProjectConstant {

    // 项目基础包名称
    public static final String BASE_PACKAGE = "com.lywq.demo";

    // Model所在包
    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".modular.generatorModular.model";

    // Mapper所在包
    public static final String MAPPER_PACKAGE = BASE_PACKAGE + ".modular.generatorModular.dao";

    // Mapper.xml所在包
    public static final String MAPPERXML_PACKAGE = ".generatorMapper";

    // Service所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".modular.generatorModular.service";

    // ServiceImpl所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";

    // Controller所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".modular.generatorModular.controller";

    // Mapper插件基础接口的完全限定名
    public static final String MAPPER_INTERFACE_REFERENCE = BASE_PACKAGE + ".common.base.Mapper";

    //文件上传储存的地址
    public static final String SAVEFILEPATH = "D:\\我的文档\\My Pictures\\upload";
}
