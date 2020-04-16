package com.lywq.demo.modular.generatorModular.service;

import com.lywq.demo.common.base.Service;
import com.lywq.demo.modular.generatorModular.dao.SystemLogMapper;
import com.lywq.demo.modular.generatorModular.model.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author lywq WED
 * @Description: SystemLogService接口
 * @date 2019/11/30 11:40
 */
@Component
public interface SystemLogService extends Service<SystemLog> {
/*
    List selectAllByID();*/
     String selectA();
    /* public void insertTest();*/

}