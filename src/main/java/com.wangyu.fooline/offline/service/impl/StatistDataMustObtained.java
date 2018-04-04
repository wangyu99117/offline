package com.wangyu.fooline.offline.service.impl;


import com.wangyu.fooline.offline.csv.FormElementFormat;
import com.wangyu.fooline.offline.demo.PageQuery;
import com.wangyu.fooline.offline.excel.ExcelFileMustObtained;
import com.wangyu.fooline.offline.execption.ExcelDataException;
import com.wangyu.fooline.offline.execption.ExcelTableHeaderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by wangyu21 on 2017/8/24.
 */
@Service("statistDataMustObtained")
public class StatistDataMustObtained implements ExcelFileMustObtained {

    private static final Logger logger = LoggerFactory.getLogger(StatistDataMustObtained.class);

    @Override
    public List<Object> find(Long entityId, Integer source, PageQuery page) throws ExcelDataException {
        return null;
    }

    @Override
    public List<Object> find(Long entityId, Integer type) throws ExcelDataException {
        List<Object> resList = new LinkedList<>();
        try{
            return resList;
        }catch (Exception e){
            logger.error("generate excel file find  fail", e);
            throw new ExcelDataException(e);
        }
    }

    @Override
    public LinkedHashMap<String, String> getColInfos(Long entityId, FormElementFormat format) throws ExcelTableHeaderException {
        return null;
    }

    @Override
    public LinkedHashMap<String, String> getColInfos(Long entityId, Integer type) throws ExcelTableHeaderException {
        LinkedHashMap<String, String> colName = new LinkedHashMap();


        return colName;
    }
}
