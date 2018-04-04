package com.wangyu.fooline.offline.excel;


import com.wangyu.fooline.offline.csv.FormElementFormat;
import com.wangyu.fooline.offline.demo.PageQuery;
import com.wangyu.fooline.offline.execption.ExcelDataException;
import com.wangyu.fooline.offline.execption.ExcelTableHeaderException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wangyu21 on 2017/8/24.
 */
public interface ExcelFileMustObtained {

    //获取数据
    List<Object> find(Long entityId, Integer source, PageQuery page) throws ExcelDataException;

    //获取数据
    List<Object> find(Long entityId, Integer type) throws ExcelDataException;

    //表头
    LinkedHashMap<String, String> getColInfos(Long entityId, FormElementFormat format) throws ExcelTableHeaderException;

    //表头
    LinkedHashMap<String, String> getColInfos(Long entityId, Integer type) throws ExcelTableHeaderException;
}
