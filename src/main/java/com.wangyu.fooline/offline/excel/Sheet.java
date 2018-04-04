package com.wangyu.fooline.offline.excel;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wangyu21 on 2017/8/23.
 */
public class Sheet<T> {

    private String name;
    private List<T> resList;
    private LinkedHashMap<String, String> colName;

    public Sheet() {
    }

    public Sheet(LinkedHashMap<String, String> colName, List<T> resList) {
        this.colName = colName;
        this.resList = resList;
    }

    public Sheet(String name, LinkedHashMap<String, String> colName, List<T> resList) {
        this.colName = colName;
        this.name = name;
        this.resList = resList;
    }

    public LinkedHashMap<String, String> getColName() {
        return colName;
    }

    public void setColName(LinkedHashMap<String, String> colName) {
        this.colName = colName;
    }

    public List<T> getResList() {
        return resList;
    }

    public void setResList(List<T> resList) {
        this.resList = resList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
