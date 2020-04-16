package com.lywq.demo.modular.generatorModular.service.impl;

import com.lywq.demo.common.base.ServiceImpl;
import com.lywq.demo.modular.generatorModular.dao.SystemLogMapper;
import com.lywq.demo.modular.generatorModular.model.SystemLog;
import com.lywq.demo.modular.generatorModular.service.SystemLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lywq WED
 * @Description: SystemLogService接口实现类
 * @date 2019/11/30 11:40
 */
@Service
public class SystemLogServiceImpl extends ServiceImpl<SystemLog> implements SystemLogService {

    @Resource
    private SystemLogMapper systemLogMapper;




    @Override
    public String selectA() {
        return systemLogMapper.selectA();
    }

//    public void insertTest(){
//        systemLogMapper.deleteByCondition(systemLogMapper);
//    }
}