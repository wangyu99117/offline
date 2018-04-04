package com.wangyu.fooline.offline.csv;


import com.wangyu.fooline.offline.demo.PageQuery;
import com.wangyu.fooline.offline.execption.CsvDataException;
import com.wangyu.fooline.offline.execption.CsvTableHeaderException;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wangyu21 on 2016/10/24.
 *
 */
public interface CsvFileMustObtained {

    //表里的数据 是一个list
    List<Object> find(Long entityId, Integer source, PageQuery page) throws CsvDataException;

    //表里的数据 是一个list
    //List<Object> find(Long entityId, PageQuery page) throws CsvDataException;

    //表头
    LinkedHashMap<String, String> getColInfos(Long entityId, FormElementFormat format) throws CsvTableHeaderException;
}
