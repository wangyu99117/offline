package com.wangyu.fooline.offline.utils.excel;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyu21 on 2016/7/28.
 */
public class ExportExcelUtils {
    private final static Logger LOG = LoggerFactory.getLogger(ExportExcelUtils.class);

    public static void exportHeader(OutputStream out, LinkedHashMap<String, String> colName){
        export(new CVSData(), out, colName);
    }

    public static void export(ExcelUtil tool, OutputStream out, LinkedHashMap<String, String> colName){
        tool.exportHeader(colName, out);
    }

    public static <T> void export(List<T> sourceList, OutputStream out, boolean pager, LinkedHashMap<String, String> colName){
        if(sourceList == null || out == null){
            return ;
        }
        if(sourceList.size() == 0 ){
            return ;
        }
        export(sourceList,  out,  new CVSData(), pager, colName);
    }

    public static <T> void export(List<T> sourceList, OutputStream out, ExcelUtil tool, boolean pager, LinkedHashMap<String, String> colName){
        if(sourceList == null || out == null){
            return ;
        }

        T  obj_1= sourceList.get(0);


        if(obj_1 instanceof Map){
            tool.export(colName, sourceList ,out, pager);
        }else{
            Class member_class = obj_1.getClass();
            tool.export(colName, member_class, sourceList ,out, pager);
        }
        //Field[] fields = ExcelAPT.getFields(member_class);

        //if(fields.length == 0){
            //LOG.error("逻辑错误！要导出的类型没有配置导出字段！");
            //return;
        //}


        //tool.export(fields, member_class, sourceList ,out, pager);
    }
}
