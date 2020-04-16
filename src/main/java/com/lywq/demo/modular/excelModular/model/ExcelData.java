package com.lywq.demo.modular.excelModular.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author lywq WED
 * @title: ExcelData
 * @projectName demo
 * @description: Excel实体类
 * @date 2019/11/8 19:43
 */
public class ExcelData implements Serializable {

    private static final long serialVersionUID = 6133772627258154184L;
    /**
     * 表头
     */
    private List<String> titles;

    /**
     * 数据
     */
    private List<List<Object>> rows;

    /**
     * 页签名称
     */
    private String name;

    public List<String> getTitles() {
        return titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
