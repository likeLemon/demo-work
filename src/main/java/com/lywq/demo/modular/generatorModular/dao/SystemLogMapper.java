package com.lywq.demo.modular.generatorModular.dao;

import com.lywq.demo.common.base.Mapper;
import com.lywq.demo.modular.generatorModular.model.SystemLog;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SystemLogMapper extends Mapper<SystemLog> {

     String selectA();

}