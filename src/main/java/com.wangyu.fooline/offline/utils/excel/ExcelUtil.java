package com.wangyu.fooline.offline.utils.excel;


import com.wangyu.fooline.offline.excel.Sheet;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by wangyu21 on 2016/7/28.
 */
public interface ExcelUtil {

    <T> void export(Field[] fields, Class target, Collection<T> dataset, OutputStream out, boolean pager);

    //<T> void export(LinkedList<String> colName, LinkedList<Field> fields, Class target, Collection<T> dataset, OutputStream out, boolean pager);

    <T> void export(LinkedHashMap<String, String> colName, Class target, Collection<T> dataset, OutputStream out, boolean pager);

    <T> void export(LinkedHashMap<String, String> colName, Collection<T> dataset, OutputStream out, boolean pager);

    <T> void  export(List<Sheet<T>> sheets, OutputStream out) throws IOException;

    void exportHeader(LinkedHashMap<String, String> colName, OutputStream out);

    //<T> void export(LinkedHashMap<String, Field> colName, Class target, Collection<T> dataset, OutputStream out, boolean pager);
}
