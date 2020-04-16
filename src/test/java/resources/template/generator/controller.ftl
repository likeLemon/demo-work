package ${basePackageController};

import ${basePackage}.common.modle.RetResult;
import ${basePackage}.common.modle.RetResponse;
import ${basePackage}.common.utils.ApplicationUtils;
import ${basePackageModel}.${modelNameUpperCamel};
import ${basePackageService}.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: ${modelNameUpperCamel}Controller类
* @author ${author}
* @date ${date}
*/
@RestController
@RequestMapping("/${baseRequestMapping}")
@Api(value = "${modelNameUpperCamel}", tags = {"${modelNameUpperCamel}操作接口"}, description = "${modelNameUpperCamel}")
public class ${modelNameUpperCamel}Controller {

@Resource
private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/insert")
    @ApiOperation(value = "新增${modelNameUpperCamel}", tags = {"${modelNameUpperCamel}操作接口"}, notes = "新增${modelNameUpperCamel}")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "${modelNameLowerCamel}", value = "${modelNameLowerCamel}", required = true)
    })
    public RetResult<Integer> insert(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
        ${modelNameLowerCamel}.setId(ApplicationUtils.getUUID());
        Integer state = ${modelNameLowerCamel}Service.insert(${modelNameLowerCamel});
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/deleteById")
    @ApiOperation(value = "删除${modelNameUpperCamel}", tags = {"${modelNameUpperCamel}操作接口"}, notes = "删除${modelNameUpperCamel}")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改${modelNameUpperCamel}", tags = {"${modelNameUpperCamel}操作接口"}, notes = "修改${modelNameUpperCamel}")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "${modelNameLowerCamel}", value = "${modelNameLowerCamel}", required = true)
    })
    public RetResult<Integer> update(${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception {
        Integer state = ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/selectById")
    @ApiOperation(value = "查询${modelNameUpperCamel}", tags = {"${modelNameUpperCamel}操作接口"}, notes = "查询${modelNameUpperCamel}")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public RetResult<${modelNameUpperCamel}> selectById(@RequestParam String id) throws Exception {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.selectById(id);
        return RetResponse.makeOKRsp(${modelNameLowerCamel});
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<${modelNameUpperCamel}>>
    */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询${modelNameUpperCamel}", tags = {"${modelNameUpperCamel}操作接口"}, notes = "分页查询${modelNameUpperCamel}")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "page", value = "当前页码"),
    @ApiImplicitParam(name = "size", value = "每页显示条数")
    })
    public RetResult<PageInfo<${modelNameUpperCamel}>> list(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.selectAll();
        PageInfo<${modelNameUpperCamel}> pageInfo = new PageInfo<${modelNameUpperCamel}>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}